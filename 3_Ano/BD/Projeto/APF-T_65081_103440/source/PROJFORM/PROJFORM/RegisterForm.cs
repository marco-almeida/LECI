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
    public partial class RegisterForm : Form
    {
        private SqlConnection cn;
        private LoginForm parent;
        public RegisterForm(LoginForm parent)
        {
            InitializeComponent();
            this.parent = parent;
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

        private void Register_Load(object sender, EventArgs e)
        {
            cn = getSGBDConnection();
        }

        private void loginButton_Click(object sender, EventArgs e)
        {
            string username = userNameBox.Text;
            string pass1 = passwordBox1.Text;
            string pass2 = passwordBox2.Text;
            if (username.Length < 4)
            {
                MessageBox.Show("O nome de utilizador deve ter pelo menos 4 carateres!");
            } else if (pass1 != pass2)
            {
                MessageBox.Show("Palavras-passe devem ser iguais!");
            } else if (pass1.Length < 4)
            {
                MessageBox.Show("A palavra-passe deve ter pelo menos 4 carateres!");
            } else
            {
                if (!verifySGBDConnection())
                    return;

                SqlCommand cmd = new SqlCommand();

                cmd.CommandText = "Exec AddDatabaseUser @username = '" + username + "', @password = '" + pass1 + "'";
                cmd.Parameters.Clear();
                cmd.Connection = cn;

                try
                {
                    cmd.ExecuteNonQuery();
                    MessageBox.Show("Conta criada com sucesso!");
                    this.parent.Show();
                    this.Close();
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
        }

        private void userNameBox_TextChanged(object sender, EventArgs e)
        {

        }

        private void goBack_Click(object sender, EventArgs e)
        {
            this.parent.Show();
            this.Close();
        }
    }
}
