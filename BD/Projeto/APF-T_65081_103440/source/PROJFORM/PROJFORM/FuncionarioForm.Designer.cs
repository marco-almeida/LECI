namespace PROJFORM{

    partial class FuncionarioForm{
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
            this.listFunc = new System.Windows.Forms.ListBox();
            this.textBoxID = new System.Windows.Forms.TextBox();
            this.textBoxStand = new System.Windows.Forms.TextBox();
            this.textBoxTelefone = new System.Windows.Forms.TextBox();
            this.textBoxEmail = new System.Windows.Forms.TextBox();
            this.textboxName = new System.Windows.Forms.TextBox();
            this.labelTelefone = new System.Windows.Forms.Label();
            this.labelEmail = new System.Windows.Forms.Label();
            this.labelStand = new System.Windows.Forms.Label();
            this.labelID = new System.Windows.Forms.Label();
            this.labelNome = new System.Windows.Forms.Label();
            this.textBoxEndereco = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.buttonAdd = new System.Windows.Forms.Button();
            this.buttonEdit = new System.Windows.Forms.Button();
            this.buttonDelete = new System.Windows.Forms.Button();
            this.buttonOK = new System.Windows.Forms.Button();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.loadAllToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.loadFromStandToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.standPortugalToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.standEspanhaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.standFrançaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.loadRoleToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.vendedorToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.mecanicoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.gerenteToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.searchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.textBoxRole = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxVendas = new System.Windows.Forms.TextBox();
            this.labelVendas = new System.Windows.Forms.Label();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.searchBox = new System.Windows.Forms.TextBox();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // listFunc
            // 
            this.listFunc.FormattingEnabled = true;
            this.listFunc.ItemHeight = 16;
            this.listFunc.Location = new System.Drawing.Point(12, 44);
            this.listFunc.Name = "listFunc";
            this.listFunc.Size = new System.Drawing.Size(381, 404);
            this.listFunc.TabIndex = 39;
            this.listFunc.SelectedIndexChanged += new System.EventHandler(this.listFuncionario_SelectedIndexChanged);
            // 
            // textBoxID
            // 
            this.textBoxID.Location = new System.Drawing.Point(565, 65);
            this.textBoxID.Name = "textBoxID";
            this.textBoxID.Size = new System.Drawing.Size(62, 22);
            this.textBoxID.TabIndex = 38;
            // 
            // textBoxStand
            // 
            this.textBoxStand.Location = new System.Drawing.Point(642, 66);
            this.textBoxStand.Name = "textBoxStand";
            this.textBoxStand.Size = new System.Drawing.Size(79, 22);
            this.textBoxStand.TabIndex = 37;
            // 
            // textBoxTelefone
            // 
            this.textBoxTelefone.Location = new System.Drawing.Point(551, 129);
            this.textBoxTelefone.Name = "textBoxTelefone";
            this.textBoxTelefone.Size = new System.Drawing.Size(170, 22);
            this.textBoxTelefone.TabIndex = 32;
            // 
            // textBoxEmail
            // 
            this.textBoxEmail.Location = new System.Drawing.Point(417, 195);
            this.textBoxEmail.Name = "textBoxEmail";
            this.textBoxEmail.Size = new System.Drawing.Size(304, 22);
            this.textBoxEmail.TabIndex = 31;
            // 
            // textboxName
            // 
            this.textboxName.Location = new System.Drawing.Point(418, 65);
            this.textboxName.Name = "textboxName";
            this.textboxName.Size = new System.Drawing.Size(130, 22);
            this.textboxName.TabIndex = 30;
            // 
            // labelTelefone
            // 
            this.labelTelefone.AutoSize = true;
            this.labelTelefone.Location = new System.Drawing.Point(549, 110);
            this.labelTelefone.Name = "labelTelefone";
            this.labelTelefone.Size = new System.Drawing.Size(64, 17);
            this.labelTelefone.TabIndex = 25;
            this.labelTelefone.Text = "Telefone";
            // 
            // labelEmail
            // 
            this.labelEmail.AutoSize = true;
            this.labelEmail.Location = new System.Drawing.Point(415, 175);
            this.labelEmail.Name = "labelEmail";
            this.labelEmail.Size = new System.Drawing.Size(42, 17);
            this.labelEmail.TabIndex = 24;
            this.labelEmail.Text = "Email";
            // 
            // labelStand
            // 
            this.labelStand.AutoSize = true;
            this.labelStand.Location = new System.Drawing.Point(639, 46);
            this.labelStand.Name = "labelStand";
            this.labelStand.Size = new System.Drawing.Size(45, 17);
            this.labelStand.TabIndex = 23;
            this.labelStand.Text = "Stand";
            // 
            // labelID
            // 
            this.labelID.AutoSize = true;
            this.labelID.Location = new System.Drawing.Point(564, 46);
            this.labelID.Name = "labelID";
            this.labelID.Size = new System.Drawing.Size(21, 17);
            this.labelID.TabIndex = 22;
            this.labelID.Text = "ID";
            // 
            // labelNome
            // 
            this.labelNome.AutoSize = true;
            this.labelNome.Location = new System.Drawing.Point(412, 46);
            this.labelNome.Name = "labelNome";
            this.labelNome.Size = new System.Drawing.Size(45, 17);
            this.labelNome.TabIndex = 21;
            this.labelNome.Text = "Nome";
            // 
            // textBoxEndereco
            // 
            this.textBoxEndereco.Location = new System.Drawing.Point(417, 257);
            this.textBoxEndereco.Name = "textBoxEndereco";
            this.textBoxEndereco.Size = new System.Drawing.Size(234, 22);
            this.textBoxEndereco.TabIndex = 42;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(414, 237);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(69, 17);
            this.label1.TabIndex = 41;
            this.label1.Text = "Endereço";
            // 
            // buttonAdd
            // 
            this.buttonAdd.Location = new System.Drawing.Point(414, 363);
            this.buttonAdd.Name = "buttonAdd";
            this.buttonAdd.Size = new System.Drawing.Size(76, 45);
            this.buttonAdd.TabIndex = 43;
            this.buttonAdd.Text = "Add";
            this.buttonAdd.UseVisualStyleBackColor = true;
            this.buttonAdd.Click += new System.EventHandler(this.buttonAdd_Click);
            // 
            // buttonEdit
            // 
            this.buttonEdit.Location = new System.Drawing.Point(514, 363);
            this.buttonEdit.Name = "buttonEdit";
            this.buttonEdit.Size = new System.Drawing.Size(75, 45);
            this.buttonEdit.TabIndex = 44;
            this.buttonEdit.Text = "Edit";
            this.buttonEdit.UseVisualStyleBackColor = true;
            this.buttonEdit.Click += new System.EventHandler(this.buttonEdit_Click);
            // 
            // buttonDelete
            // 
            this.buttonDelete.Location = new System.Drawing.Point(613, 363);
            this.buttonDelete.Name = "buttonDelete";
            this.buttonDelete.Size = new System.Drawing.Size(75, 45);
            this.buttonDelete.TabIndex = 45;
            this.buttonDelete.Text = "Delete";
            this.buttonDelete.UseVisualStyleBackColor = true;
            this.buttonDelete.Click += new System.EventHandler(this.buttonDelete_Click);
            // 
            // buttonOK
            // 
            this.buttonOK.Location = new System.Drawing.Point(433, 381);
            this.buttonOK.Name = "buttonOK";
            this.buttonOK.Size = new System.Drawing.Size(75, 60);
            this.buttonOK.TabIndex = 46;
            this.buttonOK.Text = "OK";
            this.buttonOK.UseVisualStyleBackColor = true;
            this.buttonOK.Click += new System.EventHandler(this.buttonOK_Click);
            // 
            // buttonCancel
            // 
            this.buttonCancel.Location = new System.Drawing.Point(576, 381);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(75, 60);
            this.buttonCancel.TabIndex = 47;
            this.buttonCancel.Text = "Cancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            this.buttonCancel.Click += new System.EventHandler(this.buttonCancel_Click);
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.loadAllToolStripMenuItem,
            this.loadFromStandToolStripMenuItem,
            this.loadRoleToolStripMenuItem,
            this.searchToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(754, 28);
            this.menuStrip1.TabIndex = 48;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // loadAllToolStripMenuItem
            // 
            this.loadAllToolStripMenuItem.Name = "loadAllToolStripMenuItem";
            this.loadAllToolStripMenuItem.Size = new System.Drawing.Size(78, 24);
            this.loadAllToolStripMenuItem.Text = "Load All";
            this.loadAllToolStripMenuItem.Click += new System.EventHandler(this.loadAllToolStripMenuItem_Click);
            // 
            // loadFromStandToolStripMenuItem
            // 
            this.loadFromStandToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.standPortugalToolStripMenuItem,
            this.standEspanhaToolStripMenuItem,
            this.standFrançaToolStripMenuItem});
            this.loadFromStandToolStripMenuItem.Name = "loadFromStandToolStripMenuItem";
            this.loadFromStandToolStripMenuItem.Size = new System.Drawing.Size(134, 24);
            this.loadFromStandToolStripMenuItem.Text = "Load from Stand";
            // 
            // standPortugalToolStripMenuItem
            // 
            this.standPortugalToolStripMenuItem.Name = "standPortugalToolStripMenuItem";
            this.standPortugalToolStripMenuItem.Size = new System.Drawing.Size(189, 26);
            this.standPortugalToolStripMenuItem.Text = "Stand Portugal";
            this.standPortugalToolStripMenuItem.Click += new System.EventHandler(this.standPortugalToolStripMenuItem_Click);
            // 
            // standEspanhaToolStripMenuItem
            // 
            this.standEspanhaToolStripMenuItem.Name = "standEspanhaToolStripMenuItem";
            this.standEspanhaToolStripMenuItem.Size = new System.Drawing.Size(189, 26);
            this.standEspanhaToolStripMenuItem.Text = "Stand Espanha";
            this.standEspanhaToolStripMenuItem.Click += new System.EventHandler(this.standEspanhaToolStripMenuItem_Click);
            // 
            // standFrançaToolStripMenuItem
            // 
            this.standFrançaToolStripMenuItem.Name = "standFrançaToolStripMenuItem";
            this.standFrançaToolStripMenuItem.Size = new System.Drawing.Size(189, 26);
            this.standFrançaToolStripMenuItem.Text = "Stand França";
            this.standFrançaToolStripMenuItem.Click += new System.EventHandler(this.standFrançaToolStripMenuItem_Click);
            // 
            // loadRoleToolStripMenuItem
            // 
            this.loadRoleToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.vendedorToolStripMenuItem,
            this.mecanicoToolStripMenuItem,
            this.gerenteToolStripMenuItem});
            this.loadRoleToolStripMenuItem.Name = "loadRoleToolStripMenuItem";
            this.loadRoleToolStripMenuItem.Size = new System.Drawing.Size(90, 24);
            this.loadRoleToolStripMenuItem.Text = "Load Role";
            // 
            // vendedorToolStripMenuItem
            // 
            this.vendedorToolStripMenuItem.Name = "vendedorToolStripMenuItem";
            this.vendedorToolStripMenuItem.Size = new System.Drawing.Size(156, 26);
            this.vendedorToolStripMenuItem.Text = "Vendedor";
            this.vendedorToolStripMenuItem.Click += new System.EventHandler(this.vendedorToolStripMenuItem_Click);
            // 
            // mecanicoToolStripMenuItem
            // 
            this.mecanicoToolStripMenuItem.Name = "mecanicoToolStripMenuItem";
            this.mecanicoToolStripMenuItem.Size = new System.Drawing.Size(156, 26);
            this.mecanicoToolStripMenuItem.Text = "Mecânico";
            this.mecanicoToolStripMenuItem.Click += new System.EventHandler(this.mecanicoToolStripMenuItem_Click);
            // 
            // gerenteToolStripMenuItem
            // 
            this.gerenteToolStripMenuItem.Name = "gerenteToolStripMenuItem";
            this.gerenteToolStripMenuItem.Size = new System.Drawing.Size(156, 26);
            this.gerenteToolStripMenuItem.Text = "Gerente";
            this.gerenteToolStripMenuItem.Click += new System.EventHandler(this.gerenteToolStripMenuItem_Click);
            // 
            // searchToolStripMenuItem
            // 
            this.searchToolStripMenuItem.Name = "searchToolStripMenuItem";
            this.searchToolStripMenuItem.Size = new System.Drawing.Size(67, 24);
            this.searchToolStripMenuItem.Text = "Search";
            this.searchToolStripMenuItem.Click += new System.EventHandler(this.searchToolStripMenuItem_Click);
            // 
            // textBoxRole
            // 
            this.textBoxRole.Location = new System.Drawing.Point(418, 129);
            this.textBoxRole.Name = "textBoxRole";
            this.textBoxRole.Size = new System.Drawing.Size(100, 22);
            this.textBoxRole.TabIndex = 50;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(412, 109);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(55, 17);
            this.label2.TabIndex = 49;
            this.label2.Text = "Função";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // textBoxVendas
            // 
            this.textBoxVendas.Location = new System.Drawing.Point(668, 257);
            this.textBoxVendas.Name = "textBoxVendas";
            this.textBoxVendas.Size = new System.Drawing.Size(53, 22);
            this.textBoxVendas.TabIndex = 52;
            // 
            // labelVendas
            // 
            this.labelVendas.AutoSize = true;
            this.labelVendas.Location = new System.Drawing.Point(665, 237);
            this.labelVendas.Name = "labelVendas";
            this.labelVendas.Size = new System.Drawing.Size(56, 17);
            this.labelVendas.TabIndex = 51;
            this.labelVendas.Text = "Vendas";
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // searchBox
            // 
            this.searchBox.Location = new System.Drawing.Point(403, 3);
            this.searchBox.Name = "searchBox";
            this.searchBox.Size = new System.Drawing.Size(120, 22);
            this.searchBox.TabIndex = 54;
            this.searchBox.TextChanged += new System.EventHandler(this.textBox1_TextChanged_1);
            // 
            // FuncionarioForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlLight;
            this.ClientSize = new System.Drawing.Size(754, 468);
            this.Controls.Add(this.searchBox);
            this.Controls.Add(this.textBoxVendas);
            this.Controls.Add(this.labelVendas);
            this.Controls.Add(this.textBoxRole);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.buttonOK);
            this.Controls.Add(this.buttonDelete);
            this.Controls.Add(this.buttonEdit);
            this.Controls.Add(this.buttonAdd);
            this.Controls.Add(this.textBoxEndereco);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.listFunc);
            this.Controls.Add(this.textBoxID);
            this.Controls.Add(this.textBoxStand);
            this.Controls.Add(this.textBoxTelefone);
            this.Controls.Add(this.textBoxEmail);
            this.Controls.Add(this.textboxName);
            this.Controls.Add(this.labelTelefone);
            this.Controls.Add(this.labelEmail);
            this.Controls.Add(this.labelStand);
            this.Controls.Add(this.labelID);
            this.Controls.Add(this.labelNome);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "FuncionarioForm";
            this.Text = "Funcionario";
            this.Load += new System.EventHandler(this.FuncionarioForm_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox listFunc;
        private System.Windows.Forms.TextBox textBoxID;
        private System.Windows.Forms.TextBox textBoxStand;
        private System.Windows.Forms.TextBox textBoxTelefone;
        private System.Windows.Forms.TextBox textBoxEmail;
        private System.Windows.Forms.TextBox textboxName;
        private System.Windows.Forms.Label labelTelefone;
        private System.Windows.Forms.Label labelEmail;
        private System.Windows.Forms.Label labelStand;
        private System.Windows.Forms.Label labelID;
        private System.Windows.Forms.Label labelNome;
        private System.Windows.Forms.TextBox textBoxEndereco;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buttonAdd;
        private System.Windows.Forms.Button buttonEdit;
        private System.Windows.Forms.Button buttonDelete;
        private System.Windows.Forms.Button buttonOK;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem loadAllToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem loadFromStandToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem standPortugalToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem standEspanhaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem standFrançaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem loadRoleToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem vendedorToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem mecanicoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem gerenteToolStripMenuItem;
        private System.Windows.Forms.TextBox textBoxRole;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxVendas;
        private System.Windows.Forms.Label labelVendas;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem searchToolStripMenuItem;
        private System.Windows.Forms.TextBox searchBox;
    }
}