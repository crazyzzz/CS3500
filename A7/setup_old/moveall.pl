#!/usr/bin/perl

use strict;

my @files = `ls *.java`;
chomp(@files);

foreach (@files) {
	my $aname = $_;
	$aname =~ s/\.java//;
	print "$aname\n";
	my $createcmd = "mkdir $aname";
	print "$createcmd\n";
	my $mvcmd = "mv $_ $aname/FListInteger.java";
	print "$mvcmd\n";
	my $ln1cmd = "ln -s individualmake/makefile $aname/makefile";
	print "$ln1cmd\n";
	my $ln2cmd = "ln -s $ENV{PWD}/TestFListInteger.java $aname/TestFListInteger.java";
	print "$ln2cmd\n";
	system($createcmd);
	system($mvcmd);
	system($ln1cmd);
	system($ln2cmd);
}
