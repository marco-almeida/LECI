using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PROJFORM{

    internal class Veiculo{
        private String _matricula;
        private String _marca;
        private String _modelo;

        private String _potencia;
        private String _cilindrada;
        private String _combustivel;

        private String _ano;
        private String _kms;
        private String _condicao;

        private String _stand;

        public override String ToString(){
            return _matricula + "   " + _marca + " " + _modelo;
        }

        public String Matricula{
            get { return _matricula; }
            set { _matricula = value; }
        }

        public String Marca{
            get { return _marca; }
            set { _marca = value; }
        }

        public String Modelo{
            get { return _modelo; }
            set { _modelo = value; }
        }

        public String Potencia{
            get { return _potencia; }
            set { _potencia = value; }
        }

        public String Cilindrada{
            get { return _cilindrada; }
            set { _cilindrada = value; }
        }

        public String Combustivel{
            get { return _combustivel; }
            set { _combustivel = value; }
        }

        public String Ano{
            get { return _ano; }
            set { _ano = value; }
        }

        public String Kms{
            get { return _kms; }
            set { _kms = value; }
        }

        public String Condicao{
            get { return _condicao; }
            set { _condicao = value; }
        }

        public String Stand
        {
            get { return _stand; }
            set { _stand = value; }
        }

        //Constructors
        public Veiculo() : base(){}

        public Veiculo(String Matricula, String Marca, String Modelo) : base(){
            this.Matricula = Matricula;
            this.Marca = Marca;
            this.Modelo = Modelo;
        }


    }
}
