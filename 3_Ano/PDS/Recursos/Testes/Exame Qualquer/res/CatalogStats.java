public class CatalogStats {
    private CatalogAdmin catalogAdmin;

    public CatalogStats(CatalogAdmin catalogAdmin) {
        this.catalogAdmin = catalogAdmin;
    }

    public double getAveragePriveActivities() {
        return catalogAdmin.getServicos().values().stream()
                .filter(s -> s.type() != TipoServico.PACOTE_SERVICOS && s.type() != TipoServico.ALOJAMENTO)
                .mapToDouble(Servico::price)
                .average()
                .orElse(0);
    }

    public double getMinimumPrice(TipoServico tp) {
        return catalogAdmin.getServicos().values().stream()
                .filter(s -> s.type() == tp)
                .mapToDouble(Servico::price)
                .min()
                .orElse(0);
    }

}
