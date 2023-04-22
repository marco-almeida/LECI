# 7.1

Abrir management studio **como administrador**, right click databases, attach, meter o ficheiro mdf. se der erro de permissoes, entrar na folder do ficheiro e meter todas as permissoes de admin.

Trocar a funcao getSGBDConnection(), meter o nome da conexao que aparece no login do management studio

1.

    nao sei se é assim, no Form1_Load(object sender, EventArgs e)

    meter depois do init
    loadCustomersToolStripMenuItem_Click(sender, e);

2.

    na funcao bttnEdit_Click
    if (currentContact < 0)

    na funcao listBox1_SelectedIndexChanged
    if (listBox1.SelectedIndex > -1)

3.

    adicionar antes do add na funçao loadCustomersToolStripMenuItem_Click
    C.FAX = reader["Fax"].ToString();
    C.tel = reader["Phone"].ToString(); 
