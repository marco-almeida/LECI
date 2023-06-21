using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PROJFORM
{
    public partial class Estatisticas : Form
    {
        private SqlConnection cn;

        public Estatisticas()
        {
            InitializeComponent();
            LoadGrafico();
            ConfigGrafico();
        }

        private SqlConnection getSGBDConnection()
        {
            String dbServer = "tcp: mednat.ieeta.pt\\SQLSERVER,8101";
            String dbName = "p3g2";
            String userName = "p3g2";
            String userPass = "-542790339@BD-";
            return new SqlConnection("Data Source = " + dbServer + " ;" + "Initial Catalog = " + dbName +
                                                        "; uid = " + userName + ";" + "password = " + userPass);
        }

        private bool verifySGBDConnection()
        {
            if (cn == null)
                cn = getSGBDConnection();

            if (cn.State != ConnectionState.Open)
                cn.Open();

            return cn.State == ConnectionState.Open;
        }

        void ConfigGrafico()
        {
            chart1.Titles.Add("Vendas");
            chart2.Titles.Add("Lucro");

            chart1.Series["VendasData"].Enabled = false;
            chart1.Series["VendasFunc"].Enabled = false;
            chart1.Series["VendasStand"].Enabled = true;
            chart2.Series["LucroData"].Enabled = false;
            chart2.Series["LucroFunc"].Enabled = false;
            chart2.Series["LucroStand"].Enabled = true;

            chart1.ChartAreas[0].AxisX.Title = "Stand";
            chart1.ChartAreas[0].AxisY.Title = "Nº Vendas";

            chart2.ChartAreas[0].AxisX.Title = "Stand";
            chart2.ChartAreas[0].AxisY.Title = "Euros";
        }

        void LoadGrafico()
        {
            LoadVendasData();
            LoadLucroData();
            LoadVendasFunc();
            LoadLucroFunc();
            LoadVendasStand();
            LoadLucroStand();
        }

        void LoadVendasData()
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM SalesByMonth");
            cmd.Connection = cn;
            SqlDataReader reader = cmd.ExecuteReader();

            List<string> sellDates = new List<string>();
            List<int> sellCounts = new List<int>();

            while (reader.Read())
            {
                sellDates.Add(reader["month_name"].ToString());
                sellCounts.Add(Convert.ToInt32(reader["total_vendas"]));
            }

            reader.Close();
            cn.Close();

            chart1.DataSource = sellDates;
            chart1.Series["VendasData"].Points.DataBindXY(sellDates, sellCounts);
        }

        void LoadLucroData()
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM RevenueByMonth");
            cmd.Connection = cn;
            SqlDataReader reader = cmd.ExecuteReader();

            List<string> sellDates = new List<string>();
            List<int> sellCounts = new List<int>();

            while (reader.Read())
            {
                sellDates.Add(reader["month_name"].ToString());
                sellCounts.Add(Convert.ToInt32(reader["total_montante"]));
            }

            reader.Close();
            cn.Close();

            chart2.DataSource = sellDates;
            chart2.Series["LucroData"].Points.DataBindXY(sellDates, sellCounts);
        }

        void LoadVendasFunc()
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM Vendedor");
            cmd.Connection = cn;
            SqlDataReader reader = cmd.ExecuteReader();

            List<string> sellDates = new List<string>();
            List<int> sellCounts = new List<int>();

            while (reader.Read())
            {
                sellDates.Add(reader["vendedor_id"].ToString());
                sellCounts.Add(Convert.ToInt32(reader["num_vendas"]));
            }

            reader.Close();
            cn.Close();

            chart1.DataSource = sellDates;
            chart1.Series["VendasFunc"].Points.DataBindXY(sellDates, sellCounts);
        }

        void LoadLucroFunc()
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetBestSellers");
            cmd.Connection = cn;
            SqlDataReader reader = cmd.ExecuteReader();

            List<string> sellDates = new List<string>();
            List<int> sellCounts = new List<int>();

            while (reader.Read())
            {
                sellDates.Add(reader["vendedor_id"].ToString());
                sellCounts.Add(Convert.ToInt32(reader["total_montante"]));
            }

            reader.Close();
            cn.Close();

            chart2.DataSource = sellDates;
            chart2.Series["LucroFunc"].Points.DataBindXY(sellDates, sellCounts);
        }


        void LoadVendasStand()
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetSalesByStand");
            cmd.Connection = cn;
            SqlDataReader reader = cmd.ExecuteReader();

            List<string> sellDates = new List<string>();
            List<int> sellCounts = new List<int>();

            while (reader.Read())
            {
                sellDates.Add(reader["stand_id"].ToString());
                sellCounts.Add(Convert.ToInt32(reader["total_vendas"]));
            }

            reader.Close();
            cn.Close();

            chart1.DataSource = sellDates;
            chart1.Series["VendasStand"].Points.DataBindXY(sellDates, sellCounts);
        }

        void LoadLucroStand()
        {
            if (!verifySGBDConnection())
                return;

            SqlCommand cmd = new SqlCommand("SELECT * FROM GetRevenueByStand");
            cmd.Connection = cn;
            SqlDataReader reader = cmd.ExecuteReader();

            List<string> sellDates = new List<string>();
            List<int> sellCounts = new List<int>();

            while (reader.Read())
            {
                sellDates.Add(reader["stand_id"].ToString());
                sellCounts.Add(Convert.ToInt32(reader["total_montante"]));
            }

            reader.Close();
            cn.Close();

            chart2.DataSource = sellDates;
            chart2.Series["LucroStand"].Points.DataBindXY(sellDates, sellCounts);
        }

        private void buttonVendaData_Click(object sender, EventArgs e)
        {
            chart1.Series["VendasData"].Enabled = true;
            chart1.Series["VendasFunc"].Enabled = false;
            chart1.Series["VendasStand"].Enabled = false;
            chart2.Series["LucroData"].Enabled = true;
            chart2.Series["LucroFunc"].Enabled = false;
            chart2.Series["LucroStand"].Enabled = false;

            chart1.ChartAreas[0].AxisX.Title = "";
            chart1.ChartAreas[0].AxisY.Title = "Nº Vendas";

            chart2.ChartAreas[0].AxisX.Title = "";
            chart2.ChartAreas[0].AxisY.Title = "Euros";

        }

        private void buttonVendaFunc_Click(object sender, EventArgs e)
        {
            chart1.Series["VendasData"].Enabled = false;
            chart1.Series["VendasFunc"].Enabled = true;
            chart1.Series["VendasStand"].Enabled = false;
            chart2.Series["LucroData"].Enabled = false;
            chart2.Series["LucroFunc"].Enabled = true;
            chart2.Series["LucroStand"].Enabled = false;

            chart1.ChartAreas[0].AxisX.Title = "Funcionários";
            chart1.ChartAreas[0].AxisY.Title = "Nº Vendas";

            chart2.ChartAreas[0].AxisX.Title = "Funcionários";
            chart2.ChartAreas[0].AxisY.Title = "Euros";
        }

        private void buttonVendaStand_Click(object sender, EventArgs e)
        {
            chart1.Series["VendasData"].Enabled = false;
            chart1.Series["VendasFunc"].Enabled = false;
            chart1.Series["VendasStand"].Enabled = true;
            chart2.Series["LucroData"].Enabled = false;
            chart2.Series["LucroFunc"].Enabled = false;
            chart2.Series["LucroStand"].Enabled = true;

            chart1.ChartAreas[0].AxisX.Title = "Stand";
            chart1.ChartAreas[0].AxisY.Title = "Nº Vendas";

            chart2.ChartAreas[0].AxisX.Title = "Stand";
            chart2.ChartAreas[0].AxisY.Title = "Euros";
        }


    }
}

