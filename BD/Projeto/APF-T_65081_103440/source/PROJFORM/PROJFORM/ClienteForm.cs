using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;
using System.Windows.Forms.VisualStyles;

namespace PROJFORM{

    public partial class ClienteForm : Form{
        private SqlConnection cn;
        private int currentCliente;
        private bool adding;

        public ClienteForm(){
            InitializeComponent();
        }

        private void ClienteForm_Load(object sender, EventArgs e){
            cn = getSGBDConnection();
            loadClienteScreenByX("nome");
            LockControls();
            ShowButtons();
        }

        private SqlConnection getSGBDConnection(){
            String dbServer = "tcp: mednat.ieeta.pt\\SQLSERVER,8101";
            String dbName = "p3g2";
            String userName = "p3g2";
            String userPass = "-542790339@BD-";
            return new SqlConnection("Data Source = " + dbServer + " ;" + "Initial Catalog = " + dbName +
                                                        "; uid = " + userName + ";" + "password = " + userPass);
        }

        private bool verifySGBDConnection(){
            if (cn == null)
                cn = getSGBDConnection();

            if (cn.State != ConnectionState.Open)
                cn.Open();

            return cn.State == ConnectionState.Open;
        }

        private void listCliente_SelectedIndexChanged(object sender, EventArgs e){
            if (listCliente.SelectedIndex >= 0){
                currentCliente = listCliente.SelectedIndex;
                ShowCliente();
            }
        }

        private void loadClienteScreenByX(string attr)
        {
            if (!verifySGBDConnection())
                return;
            string command = "SELECT * FROM Cliente ORDER by " + attr;

            SqlCommand cmd = new SqlCommand(command, cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listCliente.Items.Clear();

            while (reader.Read())
            {
                Cliente C = new Cliente();
                C.NIF = reader["nif"].ToString();
                C.Nome = reader["nome"].ToString();
                C.Email = reader["email"].ToString();
                C.Telefone = reader["telefone"].ToString();
                listCliente.Items.Add(C);
            }
            cn.Close();

            currentCliente = 0;
            ShowCliente();
        }

        public void ShowCliente(){
            if (listCliente.Items.Count == 0 | currentCliente < 0)
                return;
            Cliente cliente = new Cliente();
            cliente = (Cliente)listCliente.Items[currentCliente];
            textBoxNIF.Text = cliente.NIF;
            textboxName.Text = cliente.Nome;
            textBoxEmail.Text = cliente.Email;
            textBoxTelefone.Text = cliente.Telefone;
        }

        /// AUXILIARY FUNCTIONS //////////////////////////////////////////////////////////////////////////////////
        public void ShowButtons(){
            LockControls();
            buttonAdd.Visible = true;
            buttonDelete.Visible = true;
            buttonEdit.Visible = true;
            buttonOK.Visible = false;
            buttonCancel.Visible = false;
        }

        public void HideButtons(){
            UnlockControls();
            buttonAdd.Visible = false;
            buttonDelete.Visible = false;
            buttonEdit.Visible = false;
            buttonOK.Visible = true;
            buttonCancel.Visible = true;
        }

        public void LockControls(){
            textBoxNIF.ReadOnly = true;
            textboxName.ReadOnly = true;
            textBoxEmail.ReadOnly = true;
            textBoxTelefone.ReadOnly = true;
        }

        public void UnlockControls(){
            textBoxNIF.ReadOnly = false;
            textboxName.ReadOnly = false;
            textBoxEmail.ReadOnly = false;
            textBoxTelefone.ReadOnly = false;
        }

        public void ClearFields(){
            textBoxNIF.Text = "";
            textboxName.Text = "";
            textBoxEmail.Text = "";
            textBoxTelefone.Text = "";
        }

        // BUTTON FUNCITONS //////////////////////////////////////////////////////////
        private void RemoveCliente(string NIF){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            cmd.CommandText = "DELETE from Cliente WHERE NIF=@nif ";
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@nif", NIF);
            cmd.Connection = cn;

            try{
                cmd.ExecuteNonQuery();
                MessageBox.Show("Operation was successful!");
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
            }finally{
                cn.Close();
            }
        }

        private bool SaveCliente(){
            Cliente cliente = new Cliente();
            try{
                cliente.NIF = textBoxNIF.Text;
                cliente.Nome = textboxName.Text;
                cliente.Email = textBoxEmail.Text;
                cliente.Telefone = textBoxTelefone.Text;
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
                return false;
            }

            if (adding){
                SubmitCliente(cliente);
                listCliente.Items.Add(cliente);
            }else{
                UpdateCliente(cliente);
                listCliente.Items[currentCliente] = cliente;
            }
            return true;
        }


        private void SubmitCliente(Cliente C){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            cmd.CommandText = "INSERT Cliente VALUES (@NIF, @Nome, @Telefone, @Email) ";
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@NIF", C.NIF);
            cmd.Parameters.AddWithValue("@Nome", C.Nome);
            cmd.Parameters.AddWithValue("@Email", C.Email);
            cmd.Parameters.AddWithValue("@Telefone", C.Telefone);
            cmd.Connection = cn;

            try{
                cmd.ExecuteNonQuery();
                MessageBox.Show("Operation was successful!");
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
            }finally{
                cn.Close();
            }
        }

        private void UpdateCliente(Cliente C){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            cmd.CommandText = "UPDATE Cliente SET nome = @Nome, " +
                                                "email = @Email, " +
                                                "telefone = @Telefone WHERE nif = @NIF";
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@NIF", C.NIF);
            cmd.Parameters.AddWithValue("@Nome", C.Nome);
            cmd.Parameters.AddWithValue("@Email", C.Email);
            cmd.Parameters.AddWithValue("@Telefone", C.Telefone);
            cmd.Connection = cn;

            try{
                cmd.ExecuteNonQuery();
                MessageBox.Show("Operation was successful!");
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
            }finally{
                cn.Close();
            }
        }

        private void buttonAdd_Click(object sender, EventArgs e){
            adding = true;
            ClearFields();
            HideButtons();
            listCliente.Enabled = false;

        }

        private void buttonEdit_Click(object sender, EventArgs e){
            currentCliente = listCliente.SelectedIndex;
            if (currentCliente < 0){
                MessageBox.Show("Please select a contact to edit");
                return;
            }
            adding = false;
            HideButtons();
            listCliente.Enabled = false;

        }

        private void buttonDelete_Click(object sender, EventArgs e){
            if (listCliente.SelectedIndex > -1){
                try{
                    RemoveCliente(((Cliente)listCliente.SelectedItem).NIF);
                }catch (Exception ex){
                    MessageBox.Show(ex.Message);
                    return;
                }

                listCliente.Items.RemoveAt(listCliente.SelectedIndex);
                if (currentCliente == listCliente.Items.Count)
                    currentCliente = listCliente.Items.Count - 1;

                if (currentCliente == -1){
                    ClearFields();
                    MessageBox.Show("There are no more employees");
                }else{
                    ShowCliente();
                }
            }
        }

        private void buttonOK_Click(object sender, EventArgs e){
            try{
                SaveCliente();
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
            }
            listCliente.Enabled = true;
            int idx = listCliente.FindString(textBoxNIF.Text);
            listCliente.SelectedIndex = idx;
            ShowButtons();
        }

        private void buttonCancel_Click(object sender, EventArgs e){
            listCliente.Enabled = true;
            if (listCliente.Items.Count > 0){
                currentCliente = listCliente.SelectedIndex;
                if (currentCliente < 0)
                    currentCliente = 0;
                ShowCliente();
            }else{
                ClearFields();
                LockControls();
            }
            ShowButtons();
        }

        ///////////////////////////////////////////////////////

        private void nameToolStripMenuItem_Click(object sender, EventArgs e){
            loadClienteScreenByX("nome");
        }

        private void nIFToolStripMenuItem_Click(object sender, EventArgs e){
            loadClienteScreenByX("NIF");
        }

        private void searchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM cliente WHERE nome LIKE '%" + searchBox.Text + "%'", cn);

            try
            {
                SqlDataReader reader = cmd.ExecuteReader();
                listCliente.Items.Clear();

                while (reader.Read())
                {
                    Cliente C = new Cliente();
                    C.NIF = reader["nif"].ToString();
                    C.Nome = reader["nome"].ToString();
                    C.Email = reader["email"].ToString();
                    C.Telefone = reader["telefone"].ToString();
                    listCliente.Items.Add(C);
                }
                cn.Close();

                currentCliente = 0;
                ShowCliente();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                cn.Close();
            }
        }

        private void textBoxTelefone_TextChanged(object sender, EventArgs e)
        {

        }

        ///////////////////////////////////////////////////////
    }
}
