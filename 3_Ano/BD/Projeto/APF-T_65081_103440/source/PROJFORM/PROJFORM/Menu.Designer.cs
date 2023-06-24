namespace PROJFORM{

    partial class Menu{
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing){
            if (disposing && (components != null)){
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent(){
            this.components = new System.ComponentModel.Container();
            this.FuncionarioF = new System.Windows.Forms.Button();
            this.ClientesF = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.fontDialog1 = new System.Windows.Forms.FontDialog();
            this.fontDialog2 = new System.Windows.Forms.FontDialog();
            this.fontDialog3 = new System.Windows.Forms.FontDialog();
            this.fontDialog4 = new System.Windows.Forms.FontDialog();
            this.welcomeLabel = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // FuncionarioF
            // 
            this.FuncionarioF.Location = new System.Drawing.Point(50, 86);
            this.FuncionarioF.Name = "FuncionarioF";
            this.FuncionarioF.Size = new System.Drawing.Size(142, 95);
            this.FuncionarioF.TabIndex = 0;
            this.FuncionarioF.Text = "Funcionarios";
            this.FuncionarioF.UseVisualStyleBackColor = true;
            this.FuncionarioF.Click += new System.EventHandler(this.FuncionarioF_Click);
            // 
            // ClientesF
            // 
            this.ClientesF.Location = new System.Drawing.Point(281, 86);
            this.ClientesF.Name = "ClientesF";
            this.ClientesF.Size = new System.Drawing.Size(142, 95);
            this.ClientesF.TabIndex = 1;
            this.ClientesF.Text = "Clientes";
            this.ClientesF.UseVisualStyleBackColor = true;
            this.ClientesF.Click += new System.EventHandler(this.ClientesF_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(521, 86);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(142, 95);
            this.button2.TabIndex = 2;
            this.button2.Text = "Veiculos";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // welcomeLabel
            // 
            this.welcomeLabel.AutoSize = true;
            this.welcomeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.welcomeLabel.Location = new System.Drawing.Point(287, 26);
            this.welcomeLabel.Name = "welcomeLabel";
            this.welcomeLabel.Size = new System.Drawing.Size(129, 29);
            this.welcomeLabel.TabIndex = 4;
            this.welcomeLabel.Text = "Welcome!";
            this.welcomeLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.welcomeLabel.Click += new System.EventHandler(this.label1_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(50, 229);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(142, 95);
            this.button1.TabIndex = 5;
            this.button1.Text = "Estatisticas";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Menu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(714, 403);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.welcomeLabel);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.ClientesF);
            this.Controls.Add(this.FuncionarioF);
            this.Name = "Menu";
            this.Text = "Menu";
            this.Load += new System.EventHandler(this.Menu_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button FuncionarioF;
        private System.Windows.Forms.Button ClientesF;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.FontDialog fontDialog1;
        private System.Windows.Forms.FontDialog fontDialog2;
        private System.Windows.Forms.FontDialog fontDialog3;
        private System.Windows.Forms.FontDialog fontDialog4;
        private System.Windows.Forms.Label welcomeLabel;
        private System.Windows.Forms.Button button1;
    }
}