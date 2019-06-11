public class Runnable {
    public static void main(String[] args){
        Server server1 = new Server(13456, ".//src//textfil1_lasse_solen_i_ogonen.txt");
        System.out.println("running server1");
        Server server2 = new Server(23456, ".//src//textfil2_lasse_varning_for_ras.txt");
        System.out.println("running server2");
        Klient klient = new Klient(server1.getPort(), server2.getPort());
        System.out.println("running client");
    }
}
