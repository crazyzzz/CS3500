#!/usr/bin/perl

use strict;

my $whose = `ls -lart TestFListInteger.java | sed -e "s/.*-> //; s/Test.*//"`;
chomp($whose);
print "Tests by: $whose\n";

# first, get compile errors
my @compile_errors = `ls compile_errors`;
chomp(@compile_errors);

# next, test each file
my @test_dirs = `ls | grep "^su"`;
chomp(@test_dirs);

print "-"x72 . "\nPreliminary dump...\n";
print "Programs with compilation errors:\n@compile_errors\n\n";
print "Tests to be run on the following:\n@test_dirs\n";
print "-"x72 . "\n";

my %errors;

foreach (@test_dirs) {
	print "-"x72 . "\n$_\n";
	my @output = `cd $_ && java TestFListInteger 2>&1`;
	chomp(@output);
	print join("\n", @output) . "\n";
	foreach my $oline (@output) {
		if ($oline =~ /errors found in/) {
			my $errcnt = $oline;
			$errcnt =~ s/\D+.*$//;
			$errors{$_}{'ERR'} = $errcnt;
		} elsif ($oline =~ /Exception/) {
			$errors{$_}{'EX'}++;
		}
	}
	print "-"x72 . "\n";
}

print "SUMMARY BY: $whose\n";

foreach (@compile_errors) {
	print "$_\tDOES NOT COMPILE\n";
}
foreach (sort keys %errors) {
	print "$_\t";
	print "$errors{$_}{'ERR'} errors; ";
	print int($errors{$_}{'EX'}) . " improper exceptions\n";
}
