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

namespace PROJFORM{

    public partial class CompraForm : Form{
        private SqlConnection cn;
        private int currentCliente;

        public CompraForm(){
            InitializeComponent();
            loadClienteScreen();
            LockControls();
            checkBoxCarro.Checked = true;
            checkBoxMota.Checked = false;

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

        public void LockControls(){
            textBoxClientNome.ReadOnly = true;
            textBoxClientNIF.ReadOnly = true;
            textBoxPorta.ReadOnly = true;
            textBoxLugares.ReadOnly = true;
        }

        /// SHOW CLASSES //////////////////////

        public void ShowCliente(){
            if (listCliente.Items.Count == 0 | currentCliente < 0)
                return;

            Cliente cliente = new Cliente();
            cliente = (Cliente)listCliente.Items[currentCliente];
            textBoxClientNIF.Text = cliente.NIF;
            textBoxClientNome.Text = cliente.Nome;
            textBoxClientTel.Text = cliente.Telefone;
        }


        /// LOAD SCREENS ////////////////////////////////////////////

        private void loadClienteScreen(){
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM cliente ORDER by nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listCliente.Items.Clear();

            while (reader.Read()){
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


        private void listCliente_SelectedIndexChanged(object sender, EventArgs e){
            if (listCliente.SelectedIndex >= 0){
                currentCliente = listCliente.SelectedIndex;
                ShowCliente();
            }
        }

        /////////////////////////////////////////////////

        private void buttonRegist_Click(object sender, EventArgs e){
            Buy_Vehicle();
        }

        private void Buy_Vehicle(){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();

            cmd.CommandText = "BuyClientVeiculo";
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@matricula", textBoxMatricula.Text);
            cmd.Parameters.AddWithValue("@marca", textBoxMarca.Text);
            cmd.Parameters.AddWithValue("@modelo", textBoxModelo.Text);
            cmd.Parameters.AddWithValue("@combustivel", comboBoxCombustivel.Text);
            cmd.Parameters.AddWithValue("@potencia", textBoxPotencia.Text);
            cmd.Parameters.AddWithValue("@cilindrada", textBoxCilindrada.Text);
            cmd.Parameters.AddWithValue("@ano", textBoxAno.Text);
            cmd.Parameters.AddWithValue("@quilometros", textBoxKm.Text);
            cmd.Parameters.AddWithValue("@condicao", comboBoxCondicao.Text);
            cmd.Parameters.AddWithValue("@stand_id", textBoxStand.Text);
            cmd.Parameters.AddWithValue("@portas", textBoxPorta.Text);
            cmd.Parameters.AddWithValue("@lugares", textBoxLugares.Text);
            cmd.Parameters.AddWithValue("@caixa", comboBoxCaixa.Text);
            cmd.Parameters.AddWithValue("@client_nif", textBoxClientNIF.Text);
            cmd.Parameters.AddWithValue("@data_compra", dateTimePicker.Value);
            cmd.Parameters.AddWithValue("@montante", textBoxMontante.Text);
            cmd.Parameters.AddWithValue("@client_phone", textBoxClientTel.Text);

            if (checkBoxCarro.Checked == true){
                cmd.Parameters.AddWithValue("@tipo", "Carro");
            }

            if (checkBoxMota.Checked == true){
                cmd.Parameters.AddWithValue("@tipo", "Mota");
            }
            cmd.Connection = cn;

            try{
                cmd.ExecuteNonQuery();
                MessageBox.Show("Operation was successful!");
            }
            catch (Exception ex){
                MessageBox.Show(ex.Message);
            }finally{
                cn.Close();
            }
        }

        public void UnlockCarControls(){
            textBoxPorta.ReadOnly = false;
            textBoxLugares.ReadOnly = false;
        }

        public void LockCarControls(){
            textBoxPorta.ReadOnly = true;
            textBoxLugares.ReadOnly = true;
        }

        private void checkBoxCarro_CheckedChanged(object sender, EventArgs e){
            UnlockCarControls();
            checkBoxMota.Checked = false;
        }

        private void checkBoxMota_CheckedChanged(object sender, EventArgs e){
            LockCarControls();
            checkBoxCarro.Checked = false;
        }

        private void CompraForm_Load(object sender, EventArgs e)
        {

        }




        ///////////////////////////////////////////////
    }
}
