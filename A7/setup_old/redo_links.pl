#!/usr/bin/perl

use strict;

my @files = `find -type d -name "su*"`;
chomp(@files);

foreach (@files) {
	$_ =~ s/^\.\///;
	print "$_\n";
	my $ln1cmd = "ln -fs ../individualmake/makefile $_/makefile";
	print "$ln1cmd\n\n";
	system($ln1cmd);
	my $ln2cmd = "ln -fs ../TestFListInteger.java $_/TestFListInteger.java";
	print "$ln2cmd\n\n";
	system($ln2cmd);
}
