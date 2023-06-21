using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PROJFORM{
    
    internal class Cliente{
        private String _nif;
        private String _nome;
        private String _email;
        private String _telefone;

        public override String ToString(){
            return _nome + "   " + _nif;
        }

        public String NIF{
            get { return _nif; }
            set { _nif = value; }
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

    }
}
