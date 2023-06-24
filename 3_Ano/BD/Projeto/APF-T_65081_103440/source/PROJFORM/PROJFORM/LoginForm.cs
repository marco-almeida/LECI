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

namespace PROJFORM
{
    public partial class LoginForm : Form
    {
        private SqlConnection cn;
        public LoginForm()
        {
            InitializeComponent();
        }

        private SqlConnection getSGBDConnection()
        {
            String dbServer = "tcp: mednat.ieeta.pt\\SQLSERVER,8101";
            String dbName = "p3g2";
            String userName = "p3g2";
            String userPass = "-542790339@BD-";
            return new SqlConnection("Data Source = " + dbServer + " ;" + "Initial Catalog = " + dbName +
                                                        "; uid = " + userName + ";" + "password = " + userPass);
        }

        private bool verifySGBDConnection()
        {
            if (cn == null)
                cn = getSGBDConnection();

            if (cn.State != ConnectionState.Open)
                cn.Open();

            return cn.State == ConnectionState.Open;
        }

        private void fontDialog1_Apply(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click_1(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void loginButton_Click(object sender, EventArgs e)
        {
            if (!verifySGBDConnection())
                return;

            string username = userNameBox.Text;
            string password = passwordBox.Text;

            if ((username.Length == 0 && password.Length == 0) || !verifySGBDConnection())
            {
                return;
            }

            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = string.Format("DECLARE @is_valid BIT;EXEC verifyDatabaseUserCredentials '{0}', '{1}', @is_valid OUTPUT;SELECT @is_valid; ", username, password);
            cmd.Connection = cn;

            try
            {
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    bool isValid = (bool)reader[0]; // Assuming @is_valid is the first column returned

                    if (isValid)
                    {
                        MessageBox.Show("Login efetuado com sucesso! Olá " + username);
                        Menu menu = new Menu();
                        menu.Show();
                        this.Hide();
                    }
                    else
                    {
                        MessageBox.Show("Credenciais inválidas!");
                    }
                }
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

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            RegisterForm registerForm = new RegisterForm(this);
            registerForm.Show();
            this.Hide();
        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void LoginForm_Load(object sender, EventArgs e)
        {
            cn = getSGBDConnection();
        }
    }
}
