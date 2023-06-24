namespace PROJFORM
{
    partial class Estatisticas
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
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series2 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series3 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Title title1 = new System.Windows.Forms.DataVisualization.Charting.Title();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea2 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend2 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series4 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series5 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series6 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Title title2 = new System.Windows.Forms.DataVisualization.Charting.Title();
            this.chart1 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.buttonVendaData = new System.Windows.Forms.Button();
            this.buttonVendaFunc = new System.Windows.Forms.Button();
            this.buttonVendaStand = new System.Windows.Forms.Button();
            this.chart2 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart2)).BeginInit();
            this.SuspendLayout();
            // 
            // chart1
            // 
            chartArea1.Name = "ChartArea1";
            this.chart1.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.chart1.Legends.Add(legend1);
            this.chart1.Location = new System.Drawing.Point(296, 28);
            this.chart1.Name = "chart1";
            series1.ChartArea = "ChartArea1";
            series1.Legend = "Legend1";
            series1.Name = "VendasData";
            series2.ChartArea = "ChartArea1";
            series2.Legend = "Legend1";
            series2.Name = "VendasFunc";
            series3.ChartArea = "ChartArea1";
            series3.Legend = "Legend1";
            series3.Name = "VendasStand";
            this.chart1.Series.Add(series1);
            this.chart1.Series.Add(series2);
            this.chart1.Series.Add(series3);
            this.chart1.Size = new System.Drawing.Size(675, 227);
            this.chart1.TabIndex = 0;
            this.chart1.Text = "chart1";
            title1.Name = "Venda";
            this.chart1.Titles.Add(title1);
            // 
            // buttonVendaData
            // 
            this.buttonVendaData.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonVendaData.ImageAlign = System.Drawing.ContentAlignment.TopCenter;
            this.buttonVendaData.Location = new System.Drawing.Point(22, 70);
            this.buttonVendaData.Name = "buttonVendaData";
            this.buttonVendaData.Size = new System.Drawing.Size(233, 84);
            this.buttonVendaData.TabIndex = 1;
            this.buttonVendaData.Text = "Estatísticas por Data";
            this.buttonVendaData.UseVisualStyleBackColor = true;
            this.buttonVendaData.Click += new System.EventHandler(this.buttonVendaData_Click);
            // 
            // buttonVendaFunc
            // 
            this.buttonVendaFunc.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonVendaFunc.Location = new System.Drawing.Point(22, 222);
            this.buttonVendaFunc.Name = "buttonVendaFunc";
            this.buttonVendaFunc.Size = new System.Drawing.Size(233, 87);
            this.buttonVendaFunc.TabIndex = 3;
            this.buttonVendaFunc.Text = "Estatísticas por Funcionário";
            this.buttonVendaFunc.UseVisualStyleBackColor = true;
            this.buttonVendaFunc.Click += new System.EventHandler(this.buttonVendaFunc_Click);
            // 
            // buttonVendaStand
            // 
            this.buttonVendaStand.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonVendaStand.Location = new System.Drawing.Point(22, 369);
            this.buttonVendaStand.Name = "buttonVendaStand";
            this.buttonVendaStand.Size = new System.Drawing.Size(233, 87);
            this.buttonVendaStand.TabIndex = 5;
            this.buttonVendaStand.Text = "Estatísticas por Stand";
            this.buttonVendaStand.UseVisualStyleBackColor = true;
            this.buttonVendaStand.Click += new System.EventHandler(this.buttonVendaStand_Click);
            // 
            // chart2
            // 
            chartArea2.Name = "ChartArea1";
            this.chart2.ChartAreas.Add(chartArea2);
            legend2.Name = "Legend1";
            this.chart2.Legends.Add(legend2);
            this.chart2.Location = new System.Drawing.Point(296, 281);
            this.chart2.Name = "chart2";
            series4.ChartArea = "ChartArea1";
            series4.Legend = "Legend1";
            series4.Name = "LucroData";
            series5.ChartArea = "ChartArea1";
            series5.Legend = "Legend1";
            series5.Name = "LucroFunc";
            series6.ChartArea = "ChartArea1";
            series6.Legend = "Legend1";
            series6.Name = "LucroStand";
            this.chart2.Series.Add(series4);
            this.chart2.Series.Add(series5);
            this.chart2.Series.Add(series6);
            this.chart2.Size = new System.Drawing.Size(675, 227);
            this.chart2.TabIndex = 7;
            this.chart2.Text = "chart2";
            title2.Name = "Lucro";
            this.chart2.Titles.Add(title2);
            // 
            // Estatisticas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlDarkDark;
            this.ClientSize = new System.Drawing.Size(997, 545);
            this.Controls.Add(this.chart2);
            this.Controls.Add(this.buttonVendaStand);
            this.Controls.Add(this.buttonVendaFunc);
            this.Controls.Add(this.buttonVendaData);
            this.Controls.Add(this.chart1);
            this.Name = "Estatisticas";
            this.Text = "Estatisticas";
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart2)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataVisualization.Charting.Chart chart1;
        private System.Windows.Forms.Button buttonVendaData;
        private System.Windows.Forms.Button buttonVendaFunc;
        private System.Windows.Forms.Button buttonVendaStand;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart2;
    }
}