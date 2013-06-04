#!/usr/bin/perl

use strict;

my @files = `find -d -name "su*"`;
chomp(@files);

foreach (@files) {
	$_ =~ s/^\.\///;
	print "$_\n";
	my $ln1cmd = "ln -fs $ENV{PWD}/individualmake/makefile $_/makefile";
	print "$ln1cmd\n\n";
	system($ln1cmd);
}
