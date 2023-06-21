namespace PROJFORM
{
    partial class VeiculoMenu
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
            this.buttonBack = new System.Windows.Forms.Button();
            this.buttonImportar = new System.Windows.Forms.Button();
            this.buttonVenda = new System.Windows.Forms.Button();
            this.buttonCompra = new System.Windows.Forms.Button();
            this.buttonReparacao = new System.Windows.Forms.Button();
            this.buttonAluguer = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // buttonBack
            // 
            this.buttonBack.Location = new System.Drawing.Point(35, 25);
            this.buttonBack.Name = "buttonBack";
            this.buttonBack.Size = new System.Drawing.Size(93, 72);
            this.buttonBack.TabIndex = 0;
            this.buttonBack.Text = "Back";
            this.buttonBack.UseVisualStyleBackColor = true;
            this.buttonBack.Click += new System.EventHandler(this.buttonBack_Click);
            // 
            // buttonImportar
            // 
            this.buttonImportar.Location = new System.Drawing.Point(35, 129);
            this.buttonImportar.Name = "buttonImportar";
            this.buttonImportar.Size = new System.Drawing.Size(93, 72);
            this.buttonImportar.TabIndex = 1;
            this.buttonImportar.Text = "Importar";
            this.buttonImportar.UseVisualStyleBackColor = true;
            // 
            // buttonVenda
            // 
            this.buttonVenda.Location = new System.Drawing.Point(175, 25);
            this.buttonVenda.Name = "buttonVenda";
            this.buttonVenda.Size = new System.Drawing.Size(93, 72);
            this.buttonVenda.TabIndex = 2;
            this.buttonVenda.Text = "Venda";
            this.buttonVenda.UseVisualStyleBackColor = true;
            this.buttonVenda.Click += new System.EventHandler(this.buttonVenda_Click);
            // 
            // buttonCompra
            // 
            this.buttonCompra.Location = new System.Drawing.Point(307, 25);
            this.buttonCompra.Name = "buttonCompra";
            this.buttonCompra.Size = new System.Drawing.Size(93, 72);
            this.buttonCompra.TabIndex = 3;
            this.buttonCompra.Text = "Compra Cliente";
            this.buttonCompra.UseVisualStyleBackColor = true;
            this.buttonCompra.Click += new System.EventHandler(this.buttonCompra_Click);
            // 
            // buttonReparacao
            // 
            this.buttonReparacao.Location = new System.Drawing.Point(307, 129);
            this.buttonReparacao.Name = "buttonReparacao";
            this.buttonReparacao.Size = new System.Drawing.Size(93, 72);
            this.buttonReparacao.TabIndex = 4;
            this.buttonReparacao.Text = "Reparacao";
            this.buttonReparacao.UseVisualStyleBackColor = true;
            this.buttonReparacao.Click += new System.EventHandler(this.buttonReparacao_Click);
            // 
            // buttonAluguer
            // 
            this.buttonAluguer.Location = new System.Drawing.Point(175, 129);
            this.buttonAluguer.Name = "buttonAluguer";
            this.buttonAluguer.Size = new System.Drawing.Size(93, 72);
            this.buttonAluguer.TabIndex = 5;
            this.buttonAluguer.Text = "Aluguer";
            this.buttonAluguer.UseVisualStyleBackColor = true;
            this.buttonAluguer.Click += new System.EventHandler(this.buttonAluguer_Click);
            // 
            // VeiculoMenu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(461, 249);
            this.Controls.Add(this.buttonAluguer);
            this.Controls.Add(this.buttonReparacao);
            this.Controls.Add(this.buttonCompra);
            this.Controls.Add(this.buttonVenda);
            this.Controls.Add(this.buttonImportar);
            this.Controls.Add(this.buttonBack);
            this.Name = "VeiculoMenu";
            this.Text = "VeiculoMenu";
            this.Load += new System.EventHandler(this.VeiculoMenu_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button buttonBack;
        private System.Windows.Forms.Button buttonImportar;
        private System.Windows.Forms.Button buttonVenda;
        private System.Windows.Forms.Button buttonCompra;
        private System.Windows.Forms.Button buttonReparacao;
        private System.Windows.Forms.Button buttonAluguer;
    }
}