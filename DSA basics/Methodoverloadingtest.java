class Methodoverloadingtest {
    //parameterized function
    void add(int a, int b) {
        int sum = a + b+10;
        System.out.println("total sum is: " + sum);
    }
}
//inheriting properties of parent class
    class school extends Methodoverloadingtest {
//parameterized function
        void add(int a, int b) {
            int sum = a + b + 20 ;
            System.out.println("total sum is: " + sum);
        }
        public static void main(String[] args) 
        {
             //object of parent class
           school obj = new school();
            obj.add(10, 20);
            obj.add(10, 40);
        }
    }