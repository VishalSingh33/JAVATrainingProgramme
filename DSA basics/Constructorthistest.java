class Constructorthistest
{
	int a;
	int b;

	//Default constructor
	Constructorthistest()
	{
		this(10, 30);
		System.out.println("Inside default constructor \n");
	}
	
	//Parameterized constructor
	Constructorthistest(int a, int b)
	{
            //calling object of current class
		this.a = a;
		this.b = b;
		System.out.println("Inside parameterized constructor");
	}

	public static void main(String[] args)
	{
	//object created	
            Constructorthistest object = new Constructorthistest();
	}
}
