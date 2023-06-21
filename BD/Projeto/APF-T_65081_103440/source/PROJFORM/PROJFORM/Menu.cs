using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PROJFORM{

    public partial class Menu : Form{
        
        public Menu(){
            InitializeComponent();
        }

        private void FuncionarioF_Click(object sender, EventArgs e){
            FuncionarioForm funcform = new FuncionarioForm();
            funcform.Show();
        }

        private void ClientesF_Click(object sender, EventArgs e){
            ClienteForm clientform = new ClienteForm();
            clientform.Show();
        }

        private void button2_Click(object sender, EventArgs e){
            VeiculoMenu veiclform = new VeiculoMenu(this);
            veiclform.Show();
            this.Hide();
        }

        private void Menu_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Estatisticas statform = new Estatisticas();
            statform.Show();
        }

        /////////////////////////////////////////////
    }
}
