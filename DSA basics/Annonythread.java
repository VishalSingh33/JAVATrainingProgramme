class Annonythread
{
	public static void main(String[] args)
	{
            Thread t = new Thread()
		{
			public void run()
			{
				System.out.println("Child Thread");
			}
		};
		t.start();
		System.out.println("Main Thread");
	}
}
