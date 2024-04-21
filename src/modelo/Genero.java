package modelo;

public enum Genero {
    Accion(1),
    Aventura(2),
    Comedia(3),
    Drama(4),
    Fantasia(5),
    Terror(6),
    Cienciaficcion(7),
    Musical(8),
    Suspense(9),
    Western(10),
    Documental(11),
    Biografico(12),
    Romance(13);


    private int id;

    private Genero(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public static Genero buscaPorId(int id){
        for (Genero g: values()){
            if (g.id == id) {
                return g;
                
            }
        }
        return null;
    }
}
