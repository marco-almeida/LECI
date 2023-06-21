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

    public partial class VeiculoMenu : Form{
        Menu parent;

        public VeiculoMenu(Menu parent){
            InitializeComponent();
            this.parent = parent;
        }

        private void buttonBack_Click(object sender, EventArgs e){
            this.parent.Show();
            this.Close();
        }

        private void buttonVenda_Click(object sender, EventArgs e){
            VendaForm vendaForm = new VendaForm();
            vendaForm.Show();
        }

        private void buttonAluguer_Click(object sender, EventArgs e){
            AluguerForm aluguerForm = new AluguerForm();
            aluguerForm.Show();
        }

        private void buttonCompra_Click(object sender, EventArgs e){
            CompraForm compraForm = new CompraForm();
            compraForm.Show();
        }

        private void buttonReparacao_Click(object sender, EventArgs e){
            Reparacaoform reparacao = new Reparacaoform();
            reparacao.Show();
        }

        private void VeiculoMenu_Load(object sender, EventArgs e)
        {

        }

        ////////////////////////////////////////////////////
    }
}
