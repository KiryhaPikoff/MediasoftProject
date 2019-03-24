package film;

public enum Genre {
    Thrillers("Thrillers"),
    Adventure("Adventure"),
    Comedy("Comedy"),
    Mystery("Mystery"),
    Cartoons("Cartoons");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return this.genre;
    }
}
