import java.util.*;

public class Main {
    private int a;
    private int b;
    private int n;
    private int k;

    public Main(int a, int b, int n, int k) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.k = k;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public Structure createDataSet(Main m){

        StringBuilder sb_seed = new StringBuilder();
        StringBuilder sb_value = new StringBuilder();

        Structure st = new Structure(sb_seed,sb_value);

        for (int i = m.getA(); i<= m.getB(); i++) {
            Random random = new Random(i);
            for (int j = 0; j <= (m.getN() - 1); j++){
                st.sb_seed.append(i + " ");
                st.sb_value.append(random.nextInt(m.getK()) + " ");
            }
        }
        return  st;
    }


    public class Structure{
        StringBuilder sb_seed;
        StringBuilder sb_value;

        public Structure(StringBuilder sb_seed, StringBuilder sb_value) {
            this.sb_seed = sb_seed;
            this.sb_value = sb_value;
        }

        public StringBuilder getSb_seed() {
            return sb_seed;
        }

        public void setSb_seed(StringBuilder sb_seed) {
            this.sb_seed = sb_seed;
        }

        public StringBuilder getSb_value() {
            return sb_value;
        }

        public void setSb_value(StringBuilder sb_value) {
            this.sb_value = sb_value;
        }
    }

    public Structure getMaximums(Structure st, Main m){

        String[] v_seed = st.sb_seed.toString().split(" ");
        String[] v_value = st.sb_value.toString().split(" ");
        int[] vector = new int[m.getN()];
        int high = 0;
        StringBuilder out_seed = new StringBuilder();
        StringBuilder out_value = new StringBuilder();
        Structure st_out;
        int index = 0;

        //System.out.println(v_value.length);
        for(int i = 0; i <= (m.getB() - m.getA()); i++){//count seed

            for (int j = i * m.getN(); j < (i * m.getN() + m.getN()); j++){
                vector[index] = Integer.valueOf(v_value[j]);
                index ++;
            }

            for(int k = 0; k < m.getN(); k++) {
                if(vector[k] >= high){
                    high = vector[k];
                }
            }

            out_value.append(high + " ");
            out_seed.append(Integer.valueOf(v_seed[i * m.getN()]) + " ");
            high = 0;
            index = 0;

        }
        st_out = new Structure(out_seed,out_value);
        return st_out;
    }

    public Structure getMinimums(Structure st, Main m){
        String[] v_seed = st.sb_seed.toString().split(" ");
        String[] v_value = st.sb_value.toString().split(" ");
        StringBuilder out_seed = new StringBuilder();
        StringBuilder out_value = new StringBuilder();
        Structure st_out;
        int low = Integer.valueOf(v_value[0]);
        int seed = 0;

        for (int i = 0; i < (m.getB() - m.getA() + 1); i++){
            if(Integer.valueOf(v_value[i]) <= low){
                low = Integer.valueOf(v_value[i]);
                seed = Integer.valueOf(v_seed[i]);
            }
        }


        int count = 0;
        for (int i = 0; i < (m.getB() - m.getA() + 1); i++){
            if(Integer.valueOf(v_value[i]) == low){
                count++;
            }
        }

        if(count >= 0) {
            int[] vector = new int[m.getN()];
            int index = 0;
            int low2 = 0;
            for (int i = 0; i < (m.getB() - m.getA() + 1); i++){
                if(Integer.valueOf(v_value[i]) == low){
                    vector[index] = Integer.valueOf(v_seed[i]);
                    index ++;
                }
            }
            low2 = vector[0];
            for (int i = 0; i < index; i++){
                if(vector[i] <= low2){
                    seed = vector[i];
                }
            }
        }


        out_value.append(low);
        out_seed.append(seed);
        st_out = new Structure(out_seed,out_value);
        return st_out;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // write your code here
        String[] stringInput = scanner.nextLine().split(" ");
        int a = Integer.valueOf(stringInput[0]);// lower limit
        int b = Integer.valueOf(stringInput[1]);// upper limit
        int n = Integer.valueOf(stringInput[2]);// amount of numbers
        int k = Integer.valueOf(stringInput[3]);// limit of nextInt

        Main m = new Main(a, b, n, k);
        Structure st = m.createDataSet(m);
        st = m.getMaximums(st,m);
        st = m.getMinimums(st,m);

        System.out.println(st.sb_seed.toString());
        System.out.println(st.sb_value.toString()) ;

    } // end class main

}//end class MAIN



