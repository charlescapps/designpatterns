begin
    print 
"The first 30 fibonacci numbers. 
A program written in the Little Language for CS553 at PSU.
";
    prev := 0;
    curr := 1;
    n := 2; 
    while (n < 31) do
        begin
            temp := curr; 
            curr := prev + curr; 
            prev := temp;
            print "FIB(";
            print n;
            print ") = ";
            print curr;
            print "\n";
            n := n + 1
        end
end
