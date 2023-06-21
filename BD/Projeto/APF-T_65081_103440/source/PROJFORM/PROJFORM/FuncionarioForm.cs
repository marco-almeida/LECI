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
using static System.Windows.Forms.VisualStyles.VisualStyleElement.StartPanel;
using System.Xml.Linq;

namespace PROJFORM{
    
    public partial class FuncionarioForm : Form{
        private SqlConnection cn;
        private int currentFuncionario;
        private bool adding;

        public FuncionarioForm(){
            InitializeComponent();
        }

        private void FuncionarioForm_Load(object sender, EventArgs e){
            cn = getSGBDConnection();
            loadFuncionariosScreen();
            LockControls();
            ShowButtons();
            HideVendas();
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

        private void listFuncionario_SelectedIndexChanged(object sender, EventArgs e){
            if (listFunc.SelectedIndex >= 0){
                currentFuncionario = listFunc.SelectedIndex;
                ShowFuncionario();
            }
        }

        private void loadFuncionariosScreen(){
            HideVendas();
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetEmployeeRoles ORDER BY nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listFunc.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                F.Role = reader["role"].ToString();
                
                listFunc.Items.Add(F);
            }
            cn.Close();

            currentFuncionario = 0;
            ShowFuncionario();
        }

        public void ShowFuncionario(){
            if (listFunc.Items.Count == 0 | currentFuncionario < 0)
                return;

            Funcionario funcionario = new Funcionario();
            funcionario = (Funcionario)listFunc.Items[currentFuncionario];
            textBoxID.Text = funcionario.ID;
            textboxName.Text = funcionario.Nome;
            textBoxEmail.Text = funcionario.Email;
            textBoxTelefone.Text = funcionario.Telefone;
            textBoxEndereco.Text = funcionario.Endereco;
            textBoxStand.Text = funcionario.Stand;
            textBoxRole.Text = funcionario.Role;
            textBoxVendas.Text = funcionario.Vendas;
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
            textBoxID.ReadOnly = true;
            textboxName.ReadOnly = true;
            textBoxEmail.ReadOnly = true;
            textBoxTelefone.ReadOnly = true;
            textBoxEndereco.ReadOnly = true;
            textBoxStand.ReadOnly = true;
            textBoxRole.ReadOnly = true;
            textBoxVendas.ReadOnly = true;
        }

        public void UnlockControls(){
            textBoxID.ReadOnly = false;
            textboxName.ReadOnly = false;
            textBoxEmail.ReadOnly = false;
            textBoxTelefone.ReadOnly = false;
            textBoxEndereco.ReadOnly = false;
            textBoxStand.ReadOnly = false;
            textBoxRole.ReadOnly = false;
            textBoxVendas.ReadOnly = false;
        }

        public void ClearFields(){
            textBoxID.Text = "";
            textboxName.Text = "";
            textBoxEmail.Text = "";
            textBoxTelefone.Text = "";
            textBoxEndereco.Text = "";
            textBoxStand.Text = "";
        }

        public void HideVendas(){
            textBoxVendas.Visible = false;
            labelVendas.Visible = false;
        }

        // BUTTON FUNCITONS //////////////////////////////////////////////////////////
        private void buttonAdd_Click(object sender, EventArgs e){
            adding = true;
            ClearFields();
            HideButtons();
            listFunc.Enabled = false;
            textBoxEmail.Text = "@standautostaff.com";
            textBoxID.Text = "auto";
            textBoxID.ReadOnly = true;
        }


        private void buttonEdit_Click(object sender, EventArgs e){
            currentFuncionario = listFunc.SelectedIndex;
            if (currentFuncionario < 0){
                MessageBox.Show("Please select a contact to edit");
                return;
            }
            adding = false;
            HideButtons();
            listFunc.Enabled = false;
        }


        private void buttonDelete_Click(object sender, EventArgs e){
            if (listFunc.SelectedIndex > -1){
                try{
                    RemoveFuncionario(((Funcionario)listFunc.SelectedItem).ID);
                }catch (Exception ex){
                    MessageBox.Show(ex.Message);
                    return;
                }

                listFunc.Items.RemoveAt(listFunc.SelectedIndex);
                if (currentFuncionario == listFunc.Items.Count)
                    currentFuncionario = listFunc.Items.Count - 1;

                if (currentFuncionario == -1){
                    ClearFields();
                    MessageBox.Show("There are no more employees");
                }else{
                    ShowFuncionario();
                }
            }
        }

        private void RemoveFuncionario(string ID){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            cmd.CommandText = string.Format("DELETE FROM funcionario WHERE funcionario_id = {0}", ID);
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

        private void buttonOK_Click(object sender, EventArgs e){
            try{
                SaveFuncionario();
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
            }
            listFunc.Enabled = true;
            int idx = listFunc.FindString(textBoxID.Text);
            listFunc.SelectedIndex = idx;
            ShowButtons();
        }
        
        private bool SaveFuncionario(){
            Funcionario funcionario = new Funcionario();
            try{
                funcionario.ID = textBoxID.Text;
                funcionario.Nome = textboxName.Text;
                funcionario.Email = textBoxEmail.Text;
                funcionario.Telefone = textBoxTelefone.Text;
                funcionario.Endereco = textBoxEndereco.Text;
                funcionario.Stand = textBoxStand.Text;
                funcionario.Role = textBoxRole.Text;
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
                return false;
            }

            if (adding){
                SubmitFuncionario(funcionario);
                listFunc.Items.Add(funcionario);
            }else{
                UpdateFuncionario(funcionario);
                listFunc.Items[currentFuncionario] = funcionario;
            }
            return true;
        }

        private void SubmitFuncionario(Funcionario F){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            //cmd.CommandText = "INSERT Funcionario VALUES (@Nome, @Email, @Telefone, @Endereco, @Stand) ";
            cmd.CommandText = "AddEmployee";
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@Nome", F.Nome);
            cmd.Parameters.AddWithValue("@Email", F.Email);
            cmd.Parameters.AddWithValue("@Telefone", F.Telefone);
            cmd.Parameters.AddWithValue("@Endereco", F.Endereco);
            cmd.Parameters.AddWithValue("@Stand", F.Stand);
            cmd.Parameters.AddWithValue("@Role", F.Role);

            // Add output parameter for @ID
            SqlParameter idParameter = new SqlParameter("@ID", SqlDbType.Int);
            idParameter.Direction = ParameterDirection.Output;
            cmd.Parameters.Add(idParameter);

            cmd.Connection = cn;

            try{
                cmd.ExecuteNonQuery();
                // Retrieve the output value
                int generatedID = (int)cmd.Parameters["@ID"].Value;
                MessageBox.Show("Operation was successful!");
            }catch (Exception ex){
                MessageBox.Show(ex.Message);
            }finally{
                cn.Close();
            }
        }

        private void UpdateFuncionario(Funcionario F){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            cmd.CommandText = "UPDATE Funcionario SET nome = @Nome, " +
                                                "email = @Email, " +
                                                "telefone = @Telefone, " +
                                                "endereco = @Endereco, " +
                                                "stand_id = @Stand " + "WHERE funcionario_id = @ID";
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@ID", F.ID);
            cmd.Parameters.AddWithValue("@Nome", F.Nome);
            cmd.Parameters.AddWithValue("@Email", F.Email);
            cmd.Parameters.AddWithValue("@Telefone", F.Telefone);
            cmd.Parameters.AddWithValue("@Endereco", F.Endereco);
            cmd.Parameters.AddWithValue("@Stand", F.Stand);
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

        private void buttonCancel_Click(object sender, EventArgs e){
            listFunc.Enabled = true;
            if (listFunc.Items.Count > 0){
                currentFuncionario = listFunc.SelectedIndex;
                if (currentFuncionario < 0)
                    currentFuncionario = 0;
                ShowFuncionario();
            }else{
                ClearFields();
                LockControls();
            }
            ShowButtons();
        }

        // LOAD STRIPS //////////////////////////////////////////////////////////
        private void loadAllToolStripMenuItem1_Click(object sender, EventArgs e){
            HideVendas();
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetEmployeeRoles ORDER BY nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listFunc.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                F.Role = reader["role"].ToString();
                listFunc.Items.Add(F);  
            }
            cn.Close();

            currentFuncionario = 0;
            ShowFuncionario();
        }

        private void showEmployeesByStand(object sender, EventArgs e, int standId){
            HideVendas();
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetEmployeeRoles WHERE stand_id = " + standId.ToString() + " ORDER BY nome", cn);

            SqlDataReader reader = cmd.ExecuteReader();
            listFunc.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                F.Role = reader["role"].ToString();

                listFunc.Items.Add(F);
            }
            cn.Close();

            currentFuncionario = 0;
            ShowFuncionario();
        }


        private void standPortugalToolStripMenuItem_Click(object sender, EventArgs e){
            showEmployeesByStand(sender, e, 1);
        }


        private void standEspanhaToolStripMenuItem_Click(object sender, EventArgs e){
            showEmployeesByStand(sender, e, 2);
        }

        private void standFrançaToolStripMenuItem_Click(object sender, EventArgs e){
            showEmployeesByStand(sender, e, 3);
        }

        private void vendedorToolStripMenuItem_Click(object sender, EventArgs e){
            textBoxVendas.Visible = true;
            labelVendas.Visible = true;

            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM funcionario JOIN Vendedor on vendedor_id=funcionario_id ORDER by nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listFunc.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                F.Role = "Vendedor";
                F.Vendas = reader["num_vendas"].ToString();
                listFunc.Items.Add(F);
            }
            cn.Close();

            currentFuncionario = 0;
            ShowFuncionario();
        }

        private void mecanicoToolStripMenuItem_Click(object sender, EventArgs e){
            HideVendas();
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM funcionario JOIN Mecanico on mecanico_id=funcionario_id ORDER by nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listFunc.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                F.Role = "Mecanico";
                listFunc.Items.Add(F);
            }
            cn.Close();

            currentFuncionario = 0;
            ShowFuncionario();
        }

        private void gerenteToolStripMenuItem_Click(object sender, EventArgs e){
            HideVendas();
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM funcionario JOIN Gerente on gerente_id=funcionario_id ORDER by nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listFunc.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                F.Role = "Gerente";
                listFunc.Items.Add(F);
            }
            cn.Close();

            currentFuncionario = 0;
            ShowFuncionario();
        }

        private void loadAllToolStripMenuItem_Click(object sender, EventArgs e){
            loadFuncionariosScreen();
        }

        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {

        }

        private void searchToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetEmployeeRoles WHERE nome LIKE '%" + searchBox.Text + "%'", cn);
            try
            {
                SqlDataReader reader = cmd.ExecuteReader();
                listFunc.Items.Clear();

                while (reader.Read())
                {
                    Funcionario F = new Funcionario();
                    F.ID = reader["funcionario_id"].ToString();
                    F.Nome = reader["nome"].ToString();
                    F.Email = reader["email"].ToString();
                    F.Telefone = reader["telefone"].ToString();
                    F.Endereco = reader["endereco"].ToString();
                    F.Stand = reader["stand_id"].ToString();
                    F.Role = reader["role"].ToString();
                    listFunc.Items.Add(F);
                }
                cn.Close();

                currentFuncionario = 0;
                ShowFuncionario();
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

        private void label2_Click(object sender, EventArgs e)
        {

        }



        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
