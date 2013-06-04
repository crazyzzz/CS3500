#!/usr/bin/perl

if (!$ARGV[0]) {
	print "Must supply file to relink as TestFListInteger.java\n";
	exit;
}

my $file = $ARGV[0];
my $cmd = "ln -fs $file TestFListInteger.java && make";
print "$cmd\n";
system($cmd);
