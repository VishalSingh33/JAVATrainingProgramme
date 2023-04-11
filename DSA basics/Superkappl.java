class Apple {

    Apple() {

        super();
        System.out.println("This is parent default constrautor");
    }

    Apple(int x) {
        System.out.println("x=" + x);
    }

    Apple(String s) {
        System.out.println("name-" + s);
    }

}

 class superkeyapple extends Apple {

    superkeyapple() {
        super(" Aslam");
        System.out.println("This is class Test constructor");
    }

    public static void main(String[] args) {
        superkeyapple obj = new superkeyapple();

    }

}
