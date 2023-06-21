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
using System.Globalization;

namespace PROJFORM{

    public partial class Reparacaoform : Form{
        private SqlConnection cn;
        private int currentVeiculo;
        private int currentCliente;
        private int currentMecanico;

        public Reparacaoform(){
            InitializeComponent();
            loadVeiculoScreen();
            loadClienteScreen();
            loadFuncionariosScreen();
            LockControls();
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
            textBoxMatricula.ReadOnly = true;
            textBoxMarca.ReadOnly = true;
            textBoxModelo.ReadOnly = true;
            textBoxCilindrada.ReadOnly = true;
            textBoxPotencia.ReadOnly = true;
            textBoxCombustivel.ReadOnly = true;
            textBoxAno.ReadOnly = true;
            textBoxKm.ReadOnly = true;
            textBoxCondicao.ReadOnly = true;
            textBoxStand.ReadOnly = true;
            textBoxClientNome.ReadOnly = true;
            textBoxClientNIF.ReadOnly = true;
            textBoxMecanico.ReadOnly = true;
            textBoxMecID.ReadOnly = true;
        }

        /// SHOW CLASSES //////////////////////
        public void ShowVeiculo(){
            if (listVeiculo.Items.Count == 0 | currentVeiculo < 0)
                return;

            Veiculo veiculo = new Veiculo();
            veiculo = (Veiculo)listVeiculo.Items[currentVeiculo];
            textBoxMatricula.Text = veiculo.Matricula;
            textBoxMarca.Text = veiculo.Marca;
            textBoxModelo.Text = veiculo.Modelo;
            textBoxPotencia.Text = veiculo.Potencia;
            textBoxCilindrada.Text = veiculo.Cilindrada;
            textBoxCombustivel.Text = veiculo.Combustivel;
            textBoxAno.Text = veiculo.Ano;
            textBoxKm.Text = veiculo.Kms;
            textBoxCondicao.Text = veiculo.Condicao;
            textBoxStand.Text = veiculo.Stand;
        }

        public void ShowCliente(){
            if (listCliente.Items.Count == 0 | currentCliente < 0)
                return;

            Cliente cliente = new Cliente();
            cliente = (Cliente)listCliente.Items[currentCliente];
            textBoxClientNIF.Text = cliente.NIF;
            textBoxClientNome.Text = cliente.Nome;
            textBoxClientTel.Text = cliente.Telefone;
        }

        public void ShowMecanico(){
            if (listMecanico.Items.Count == 0 | currentMecanico < 0)
                return;

            Funcionario funcionario = new Funcionario();
            funcionario = (Funcionario)listMecanico.Items[currentMecanico];
            textBoxMecanico.Text = funcionario.Nome;
            textBoxMecID.Text = funcionario.ID;
        }

        /// LOAD SCREENS ////////////////////////////////////////////
        private void loadVeiculoScreen(){
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM veiculo WHERE stand_id IS NULL", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listVeiculo.Items.Clear();

            while (reader.Read()){
                Veiculo V = new Veiculo();
                V.Matricula = reader["matricula"].ToString();
                V.Marca = reader["marca"].ToString();
                V.Modelo = reader["modelo"].ToString();
                V.Potencia = reader["potencia"].ToString();
                V.Cilindrada = reader["cilindrada"].ToString();
                V.Combustivel = reader["combustivel"].ToString();
                V.Ano = reader["ano"].ToString();
                V.Kms = reader["quilometros"].ToString();
                V.Condicao = reader["Condicao"].ToString();
                V.Stand = reader["Stand_id"].ToString();
                listVeiculo.Items.Add(V);
            }
            cn.Close();

            currentVeiculo = 0;
            ShowVeiculo();
        }

        private void loadClienteScreen(){
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM Cliente JOIN venda ON cliente.nif=venda.cliente ", cn);
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

        private void loadFuncionariosScreen(){
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM Funcionario JOIN mecanico ON mecanico_id=funcionario_id ORDER by nome", cn);
            SqlDataReader reader = cmd.ExecuteReader();
            listMecanico.Items.Clear();

            while (reader.Read()){
                Funcionario F = new Funcionario();
                F.ID = reader["funcionario_id"].ToString();
                F.Nome = reader["nome"].ToString();
                F.Email = reader["email"].ToString();
                F.Telefone = reader["telefone"].ToString();
                F.Endereco = reader["endereco"].ToString();
                F.Stand = reader["stand_id"].ToString();
                listMecanico.Items.Add(F);
            }
            cn.Close();

            currentMecanico = 0;
            ShowMecanico();
        }

        private void listVeiculo_SelectedIndexChanged(object sender, EventArgs e){
            if (listVeiculo.SelectedIndex >= 0){
                currentVeiculo = listVeiculo.SelectedIndex;
                ShowVeiculo();
            }
        }

        private void listCliente_SelectedIndexChanged(object sender, EventArgs e){
            if (listCliente.SelectedIndex >= 0){
                currentCliente = listCliente.SelectedIndex;
                ShowCliente();
            }
        }

        private void listMecanico_SelectedIndexChanged(object sender, EventArgs e){
            if (listMecanico.SelectedIndex >= 0){
                currentMecanico = listMecanico.SelectedIndex;
                ShowMecanico();
            }
        }

        /////////////////////////////////////////////////
        private void buttonRegist_Click(object sender, EventArgs e){
            RepairClientVehicle();
        }

        private void RepairClientVehicle(){
            if (!verifySGBDConnection())
                return;
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "RepairClientVeiculo";
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Parameters.Clear();
            cmd.Parameters.AddWithValue("@matricula", textBoxMatricula.Text);
            cmd.Parameters.AddWithValue("@client_nif", textBoxClientNIF.Text);
            cmd.Parameters.AddWithValue("@data_inicio", dateTimePicker1.Value.Date);
            cmd.Parameters.AddWithValue("@data_fim", dateTimePicker2.Value.Date);
            cmd.Parameters.AddWithValue("@montante", textBoxMontante.Text);
            cmd.Parameters.AddWithValue("@client_phone", textBoxClientTel.Text);
            cmd.Parameters.AddWithValue("@mecanico", textBoxMecID.Text);
            cmd.Connection = cn;

            try{
                cmd.ExecuteNonQuery();
                MessageBox.Show("Operation was successful!");
            }
            catch (Exception ex){
                MessageBox.Show(ex.Message);
            }
            finally{
                cn.Close();
            }

        }

        private void Reparacaoform_Load(object sender, EventArgs e)
        {

        }
    }
}
