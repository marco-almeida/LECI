using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PROJFORM{

    internal class Funcionario{
        private String _id;
        private String _nome;
        private String _email;
        private String _telefone;
        private String _endereco;
        private String _stand;
        private String _role;
        private String _vendas;

        public override String ToString(){
            return _nome + "   " + _id;
        }

        public String ID{
            get { return _id; }
            set { _id = value; }
        }

        public String Nome{
            get { return _nome; }
            set { _nome = value; }
        }

        public String Email{
            get { return _email; }
            set { _email = value; }
        }

        public String Telefone{
            get { return _telefone; }
            set { _telefone = value; }
        }

        public String Endereco{
            get { return _endereco; }
            set { _endereco = value; }
        }

        public String Stand{
            get { return _stand; }
            set { _stand = value; }
        }

        public String Role{
            get { return _role; }
            set { _role = value; }
        }

        public String Vendas{
            get { return _vendas; }
            set { _vendas = value; }
        }


        //Constructors
        public Funcionario() : base(){}

        public Funcionario(String ID, String Nome) : base(){
            this.ID = ID;
            this.Nome = Nome;
        }

    //////////////////////////////////////////////////////////////
    }
}
