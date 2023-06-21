namespace PROJFORM
{
    partial class VendaForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.listVeiculo = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxMatricula = new System.Windows.Forms.TextBox();
            this.textBoxModelo = new System.Windows.Forms.TextBox();
            this.textBoxPotencia = new System.Windows.Forms.TextBox();
            this.textBoxCilindrada = new System.Windows.Forms.TextBox();
            this.textBoxMarca = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.labelMatricula = new System.Windows.Forms.Label();
            this.listVendedor = new System.Windows.Forms.ListBox();
            this.listCliente = new System.Windows.Forms.ListBox();
            this.textBoxCombustivel = new System.Windows.Forms.TextBox();
            this.textBoxCondicao = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.textBoxAno = new System.Windows.Forms.TextBox();
            this.textBoxKm = new System.Windows.Forms.TextBox();
            this.textBoxClientTel = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.textBoxClientNome = new System.Windows.Forms.TextBox();
            this.textBoxClientNIF = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.textBoxVendedor = new System.Windows.Forms.TextBox();
            this.label14 = new System.Windows.Forms.Label();
            this.textBoxVendID = new System.Windows.Forms.TextBox();
            this.label15 = new System.Windows.Forms.Label();
            this.label16 = new System.Windows.Forms.Label();
            this.textBoxMontante = new System.Windows.Forms.TextBox();
            this.label17 = new System.Windows.Forms.Label();
            this.label18 = new System.Windows.Forms.Label();
            this.label19 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.panel2 = new System.Windows.Forms.Panel();
            this.buttonRegist = new System.Windows.Forms.Button();
            this.label20 = new System.Windows.Forms.Label();
            this.textBoxStand = new System.Windows.Forms.TextBox();
            this.dateTimePicker1 = new System.Windows.Forms.DateTimePicker();
            this.comboMetodoPagamento = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // listVeiculo
            // 
            this.listVeiculo.FormattingEnabled = true;
            this.listVeiculo.ItemHeight = 16;
            this.listVeiculo.Location = new System.Drawing.Point(12, 28);
            this.listVeiculo.Name = "listVeiculo";
            this.listVeiculo.Size = new System.Drawing.Size(329, 132);
            this.listVeiculo.TabIndex = 0;
            this.listVeiculo.SelectedIndexChanged += new System.EventHandler(this.listVeiculo_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(69, 17);
            this.label1.TabIndex = 1;
            this.label1.Text = "Veiculos";
            // 
            // textBoxMatricula
            // 
            this.textBoxMatricula.Location = new System.Drawing.Point(381, 28);
            this.textBoxMatricula.Name = "textBoxMatricula";
            this.textBoxMatricula.Size = new System.Drawing.Size(100, 22);
            this.textBoxMatricula.TabIndex = 2;
            // 
            // textBoxModelo
            // 
            this.textBoxModelo.Location = new System.Drawing.Point(659, 28);
            this.textBoxModelo.Name = "textBoxModelo";
            this.textBoxModelo.Size = new System.Drawing.Size(100, 22);
            this.textBoxModelo.TabIndex = 3;
            // 
            // textBoxPotencia
            // 
            this.textBoxPotencia.Location = new System.Drawing.Point(521, 82);
            this.textBoxPotencia.Name = "textBoxPotencia";
            this.textBoxPotencia.Size = new System.Drawing.Size(100, 22);
            this.textBoxPotencia.TabIndex = 4;
            // 
            // textBoxCilindrada
            // 
            this.textBoxCilindrada.Location = new System.Drawing.Point(381, 82);
            this.textBoxCilindrada.Name = "textBoxCilindrada";
            this.textBoxCilindrada.Size = new System.Drawing.Size(100, 22);
            this.textBoxCilindrada.TabIndex = 5;
            // 
            // textBoxMarca
            // 
            this.textBoxMarca.Location = new System.Drawing.Point(521, 28);
            this.textBoxMarca.Name = "textBoxMarca";
            this.textBoxMarca.Size = new System.Drawing.Size(100, 22);
            this.textBoxMarca.TabIndex = 6;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(656, 63);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(84, 17);
            this.label2.TabIndex = 7;
            this.label2.Text = "Combustível";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(518, 63);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(63, 17);
            this.label3.TabIndex = 8;
            this.label3.Text = "Potencia";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(378, 63);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(71, 17);
            this.label4.TabIndex = 9;
            this.label4.Text = "Cilindrada";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(656, 9);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(54, 17);
            this.label5.TabIndex = 10;
            this.label5.Text = "Modelo";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(518, 9);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(47, 17);
            this.label6.TabIndex = 11;
            this.label6.Text = "Marca";
            // 
            // labelMatricula
            // 
            this.labelMatricula.AutoSize = true;
            this.labelMatricula.Location = new System.Drawing.Point(378, 9);
            this.labelMatricula.Name = "labelMatricula";
            this.labelMatricula.Size = new System.Drawing.Size(65, 17);
            this.labelMatricula.TabIndex = 12;
            this.labelMatricula.Text = "Matricula";
            // 
            // listVendedor
            // 
            this.listVendedor.FormattingEnabled = true;
            this.listVendedor.ItemHeight = 16;
            this.listVendedor.Location = new System.Drawing.Point(12, 376);
            this.listVendedor.Name = "listVendedor";
            this.listVendedor.Size = new System.Drawing.Size(329, 164);
            this.listVendedor.TabIndex = 13;
            this.listVendedor.SelectedIndexChanged += new System.EventHandler(this.listVendedor_SelectedIndexChanged);
            // 
            // listCliente
            // 
            this.listCliente.FormattingEnabled = true;
            this.listCliente.ItemHeight = 16;
            this.listCliente.Location = new System.Drawing.Point(12, 225);
            this.listCliente.Name = "listCliente";
            this.listCliente.Size = new System.Drawing.Size(329, 84);
            this.listCliente.TabIndex = 14;
            this.listCliente.SelectedIndexChanged += new System.EventHandler(this.listCliente_SelectedIndexChanged);
            // 
            // textBoxCombustivel
            // 
            this.textBoxCombustivel.Location = new System.Drawing.Point(659, 82);
            this.textBoxCombustivel.Name = "textBoxCombustivel";
            this.textBoxCombustivel.Size = new System.Drawing.Size(100, 22);
            this.textBoxCombustivel.TabIndex = 15;
            // 
            // textBoxCondicao
            // 
            this.textBoxCondicao.Location = new System.Drawing.Point(659, 136);
            this.textBoxCondicao.Name = "textBoxCondicao";
            this.textBoxCondicao.Size = new System.Drawing.Size(100, 22);
            this.textBoxCondicao.TabIndex = 21;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(378, 117);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(33, 17);
            this.label7.TabIndex = 20;
            this.label7.Text = "Ano";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(518, 117);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(35, 17);
            this.label8.TabIndex = 19;
            this.label8.Text = "Kms";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(656, 117);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(67, 17);
            this.label9.TabIndex = 18;
            this.label9.Text = "Condição";
            // 
            // textBoxAno
            // 
            this.textBoxAno.Location = new System.Drawing.Point(381, 136);
            this.textBoxAno.Name = "textBoxAno";
            this.textBoxAno.Size = new System.Drawing.Size(100, 22);
            this.textBoxAno.TabIndex = 17;
            // 
            // textBoxKm
            // 
            this.textBoxKm.Location = new System.Drawing.Point(521, 136);
            this.textBoxKm.Name = "textBoxKm";
            this.textBoxKm.Size = new System.Drawing.Size(100, 22);
            this.textBoxKm.TabIndex = 16;
            // 
            // textBoxClientTel
            // 
            this.textBoxClientTel.Location = new System.Drawing.Point(659, 225);
            this.textBoxClientTel.Name = "textBoxClientTel";
            this.textBoxClientTel.Size = new System.Drawing.Size(100, 22);
            this.textBoxClientTel.TabIndex = 27;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(378, 206);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(92, 17);
            this.label10.TabIndex = 26;
            this.label10.Text = "Nome Cliente";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(518, 206);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(76, 17);
            this.label11.TabIndex = 25;
            this.label11.Text = "NIF Cliente";
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(656, 206);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(120, 17);
            this.label12.TabIndex = 24;
            this.label12.Text = "Telemovel Cliente";
            // 
            // textBoxClientNome
            // 
            this.textBoxClientNome.Location = new System.Drawing.Point(381, 225);
            this.textBoxClientNome.Name = "textBoxClientNome";
            this.textBoxClientNome.Size = new System.Drawing.Size(100, 22);
            this.textBoxClientNome.TabIndex = 23;
            // 
            // textBoxClientNIF
            // 
            this.textBoxClientNIF.Location = new System.Drawing.Point(521, 225);
            this.textBoxClientNIF.Name = "textBoxClientNIF";
            this.textBoxClientNIF.Size = new System.Drawing.Size(100, 22);
            this.textBoxClientNIF.TabIndex = 22;
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(375, 357);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(70, 17);
            this.label13.TabIndex = 29;
            this.label13.Text = "Vendedor";
            // 
            // textBoxVendedor
            // 
            this.textBoxVendedor.Location = new System.Drawing.Point(378, 376);
            this.textBoxVendedor.Name = "textBoxVendedor";
            this.textBoxVendedor.Size = new System.Drawing.Size(100, 22);
            this.textBoxVendedor.TabIndex = 28;
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(515, 357);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(21, 17);
            this.label14.TabIndex = 31;
            this.label14.Text = "ID";
            // 
            // textBoxVendID
            // 
            this.textBoxVendID.Location = new System.Drawing.Point(518, 376);
            this.textBoxVendID.Name = "textBoxVendID";
            this.textBoxVendID.Size = new System.Drawing.Size(100, 22);
            this.textBoxVendID.TabIndex = 30;
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(378, 419);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(67, 17);
            this.label15.TabIndex = 32;
            this.label15.Text = "Montante";
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(654, 417);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(38, 17);
            this.label16.TabIndex = 33;
            this.label16.Text = "Data";
            // 
            // textBoxMontante
            // 
            this.textBoxMontante.Location = new System.Drawing.Point(381, 438);
            this.textBoxMontante.Name = "textBoxMontante";
            this.textBoxMontante.Size = new System.Drawing.Size(100, 22);
            this.textBoxMontante.TabIndex = 34;
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Location = new System.Drawing.Point(517, 419);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(131, 17);
            this.label17.TabIndex = 37;
            this.label17.Text = "Metodo Pagamento";
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label18.Location = new System.Drawing.Point(12, 206);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(58, 17);
            this.label18.TabIndex = 38;
            this.label18.Text = "Cliente";
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label19.Location = new System.Drawing.Point(9, 357);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(78, 17);
            this.label19.TabIndex = 39;
            this.label19.Text = "Vendedor";
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.SystemColors.ButtonShadow;
            this.panel1.Location = new System.Drawing.Point(1, 176);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(916, 10);
            this.panel1.TabIndex = 40;
            // 
            // panel2
            // 
            this.panel2.BackColor = System.Drawing.SystemColors.ButtonShadow;
            this.panel2.Location = new System.Drawing.Point(1, 326);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(916, 10);
            this.panel2.TabIndex = 41;
            // 
            // buttonRegist
            // 
            this.buttonRegist.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonRegist.Location = new System.Drawing.Point(381, 497);
            this.buttonRegist.Name = "buttonRegist";
            this.buttonRegist.Size = new System.Drawing.Size(270, 47);
            this.buttonRegist.TabIndex = 42;
            this.buttonRegist.Text = "Registar Venda";
            this.buttonRegist.UseVisualStyleBackColor = true;
            this.buttonRegist.Click += new System.EventHandler(this.buttonRegist_Click);
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Location = new System.Drawing.Point(779, 9);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(45, 17);
            this.label20.TabIndex = 44;
            this.label20.Text = "Stand";
            // 
            // textBoxStand
            // 
            this.textBoxStand.Location = new System.Drawing.Point(782, 28);
            this.textBoxStand.Name = "textBoxStand";
            this.textBoxStand.Size = new System.Drawing.Size(100, 22);
            this.textBoxStand.TabIndex = 43;
            // 
            // dateTimePicker1
            // 
            this.dateTimePicker1.Location = new System.Drawing.Point(659, 436);
            this.dateTimePicker1.Name = "dateTimePicker1";
            this.dateTimePicker1.Size = new System.Drawing.Size(200, 22);
            this.dateTimePicker1.TabIndex = 45;
            // 
            // comboMetodoPagamento
            // 
            this.comboMetodoPagamento.FormattingEnabled = true;
            this.comboMetodoPagamento.Items.AddRange(new object[] {
            "Dinheiro",
            "Cheque",
            "Transferencia",
            "Cartao de Credito"});
            this.comboMetodoPagamento.Location = new System.Drawing.Point(518, 438);
            this.comboMetodoPagamento.Name = "comboMetodoPagamento";
            this.comboMetodoPagamento.Size = new System.Drawing.Size(121, 24);
            this.comboMetodoPagamento.TabIndex = 46;
            // 
            // VendaForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(917, 556);
            this.Controls.Add(this.comboMetodoPagamento);
            this.Controls.Add(this.dateTimePicker1);
            this.Controls.Add(this.label20);
            this.Controls.Add(this.textBoxStand);
            this.Controls.Add(this.buttonRegist);
            this.Controls.Add(this.panel2);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.label19);
            this.Controls.Add(this.label18);
            this.Controls.Add(this.label17);
            this.Controls.Add(this.textBoxMontante);
            this.Controls.Add(this.label16);
            this.Controls.Add(this.label15);
            this.Controls.Add(this.label14);
            this.Controls.Add(this.textBoxVendID);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.textBoxVendedor);
            this.Controls.Add(this.textBoxClientTel);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.textBoxClientNome);
            this.Controls.Add(this.textBoxClientNIF);
            this.Controls.Add(this.textBoxCondicao);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.textBoxAno);
            this.Controls.Add(this.textBoxKm);
            this.Controls.Add(this.textBoxCombustivel);
            this.Controls.Add(this.listCliente);
            this.Controls.Add(this.listVendedor);
            this.Controls.Add(this.labelMatricula);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.textBoxMarca);
            this.Controls.Add(this.textBoxCilindrada);
            this.Controls.Add(this.textBoxPotencia);
            this.Controls.Add(this.textBoxModelo);
            this.Controls.Add(this.textBoxMatricula);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.listVeiculo);
            this.Name = "VendaForm";
            this.Text = "VendaForm";
            this.Load += new System.EventHandler(this.VendaForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox listVeiculo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxMatricula;
        private System.Windows.Forms.TextBox textBoxModelo;
        private System.Windows.Forms.TextBox textBoxPotencia;
        private System.Windows.Forms.TextBox textBoxCilindrada;
        private System.Windows.Forms.TextBox textBoxMarca;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label labelMatricula;
        private System.Windows.Forms.ListBox listVendedor;
        private System.Windows.Forms.ListBox listCliente;
        private System.Windows.Forms.TextBox textBoxCombustivel;
        private System.Windows.Forms.TextBox textBoxCondicao;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox textBoxAno;
        private System.Windows.Forms.TextBox textBoxKm;
        private System.Windows.Forms.TextBox textBoxClientTel;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.TextBox textBoxClientNome;
        private System.Windows.Forms.TextBox textBoxClientNIF;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.TextBox textBoxVendedor;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.TextBox textBoxVendID;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.TextBox textBoxMontante;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Button buttonRegist;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.TextBox textBoxStand;
        private System.Windows.Forms.DateTimePicker dateTimePicker1;
        private System.Windows.Forms.ComboBox comboMetodoPagamento;
    }
}