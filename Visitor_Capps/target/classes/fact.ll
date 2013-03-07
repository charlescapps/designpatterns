begin
    fact := 1;
    n := 12;
    while (n > 1) do
        begin
            fact := fact * n;
            n := n - 1
        end
    ;
    print "12! = ";
    print fact;
    print "\n";
    print "n = ";
    print n;
    print "\n"
end
