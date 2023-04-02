package na.ma.numper.series.model;

public class PersonsDetails {
    private int id ;
    private String image ;//
    private String full_name ;//
    private String email ;//
    private String user_name ;//
    private String password;
    private int age;
    private int Score ;//
    private String card_timeAndDate ;//

    private String new_password ;

    public PersonsDetails()
    {

    }
    // كونستركتور ليأخذ بعض البيانات يلي دخلها المستخدم في شاشة الريجستر
    public PersonsDetails(String new_password) {
        this.new_password = new_password;
    }

    public PersonsDetails(String full_name , int score , String card_timeAndDate){
        this.full_name = full_name;
        this.Score = score;
        this.card_timeAndDate = card_timeAndDate ;
    }

    public PersonsDetails(int id, String full_name, int score, String card_timeAndDate) {
        this.id = id;
        this.full_name = full_name;
        this.Score = score;
        this.card_timeAndDate = card_timeAndDate;
    }

     public PersonsDetails(String image, String full_name, String email, String user_name,String password, int age) {
        this.image = image;
        this.full_name = full_name;
        this.email = email;
        this.user_name = user_name;
        this.password=password;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    public String getCard_timeAndDate() {
        return card_timeAndDate;
    }

    public void setCard_timeAndDate(String card_timeAndDate) {
        this.card_timeAndDate = card_timeAndDate;
    }


    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }


}
