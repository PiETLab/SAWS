#!/gnuplot
###########################
## OUTPUT
# set output
#set terminal windows color "Arial" 10
#set terminal windows color "TimesRoman" 12
#set terminal windows color "TimesRoman" 10
#set terminal windows color 10
#set terminal postscript eps enhanced "Times-Roman" 14
set terminal postscript color eps enhanced "Times-Roman" 14
set output "scatter3.eps"
set lmargin -1
set bmargin -1
set rmargin -1
set tmargin -1
###########################
### UNKNOWN THINGS
# this sets the style for the plotting element "fill"
set style fill solid 1.000000 border
set loadpath 
set fontpath 
set boxwidth
unset label
unset arrow
unset style line
#unset grid
unset style arrow
unset logscale
set offsets 0, 0, 0, 0
set pointsize 1
set encoding default
unset polar
unset parametric
unset decimalsign
set samples 100, 100
#set isosamples 10, 10
set isosamples 2, 2
set surface
unset contour
set mapping cartesian
unset hidden3d
set cntrparam order 4
set cntrparam cubicspline
set cntrparam levels auto 10
set cntrparam points 5
set size ratio 0 1,1
set origin 0,0
set timestamp "" bottom norotate 0.000000,0.000000  ""
set zero 1e-008
################################
### CONTOUR-RELATED
set clabel '%8.3g'
################################
## The locale setting determines the language with which
## `{x,y,z}{d,m}tics` will write the days and months.
set locale "C"
################################
### DEFINITION OF LINE STYLES
set style line 1 linetype 8 linewidth 1.000 pointtype 1 pointsize 1.000
set style line 2 linetype 8 linewidth 3.000 pointtype 2 pointsize 1.000
set style line 3 linetype 8 linewidth 3.000 pointtype 3 pointsize 1.000
set style line 4 linetype 8 linewidth 3.000 pointtype 4 pointsize 1.000
set style line 5 linetype 8 linewidth 3.000 pointtype 5 pointsize 1.000
set style line 6 linetype 8 linewidth 3.000 pointtype 6 pointsize 1.000
set style line 7 linetype 8 linewidth 3.000 pointtype 7 pointsize 1.000
set style line 8 linetype 8 linewidth 3.000 pointtype 8 pointsize 1.000
set style line 9 linetype 8 linewidth 3.000 pointtype 9 pointsize 1.000
set style line 10 linetype 8 linewidth 3.000 pointtype 10 pointsize 1.000
set style line 11 linetype 8 linewidth 3.000 pointtype 11 pointsize 1.000
#############################
### COLORBOX OPTIONS
set palette positive nops_allcF maxcolors 0 gamma 1.5 gray
set format cb "% g"
set colorbox default
set colorbox vertical origin 0.9,0.2 size 0.1,0.63 bdefault
set cbtics border mirror norotate autofreq 
set cblabel "" 0.000000,0.000000  font ""
set cbrange [ * : * ] noreverse nowriteback  # (currently [-10.0000:10.0000] )#
set mcbtics default
############################
### KEY OPTIONS
set key title "Text Comp. Facility"
#unset key
##########################
## Set the co-ordinate system
# (default is rectangular)
#set polar
############################
### GENERAL PLOTTING OPTIONS
set border 31 lt -1 lw 1.000
#unset border
############################
### 3D PLOTTING
set pm3d at s
#set pm3d scansforward flush center ftriangles nohidden3d implicit corners2color mean
set pm3d scansforward flush center ftriangles nohidden3d explicit corners2color mean
set style function lines
set style data linespoints
#set style data points
set palette gray 
################################
### TIC MARKS
set tics in
#set tics out
# The size of the tic marks can be adjusted with ticscale.
set ticscale 1 0.5
########################################
### X AXIS
# the other option: set xdata time
set xdata
set x2data
set format x "% g"
set format x2 "% g"
set xzeroaxis lt -2 lw 1.000
set x2zeroaxis lt -2 lw 1.000
set xtics border nomirror norotate autofreq 
#unset xtics
#set xtics 0,0.1,0.45
set mxtics default
set mx2tics default
#set xtics 1,0.25,2
#set xlabel "{/Symbol l}_{}" 4,-2  font "Times-Roman,18"
#set xlabel "x-axis" 4,-2  font "Times-Roman,18"
set xlabel "Mean Number of Wait Actions"  font "Times-Roman,18"
set xrange [ * : * ] noreverse nowriteback  
set x2range [ * : * ] noreverse nowriteback  # (currently [-10.0000:10.0000] )
set x2label "" 0.000000,0.000000  font ""
set nox2tics
########################################
### Y AXIS
set ydata
set y2data
set format y "% g"
set format y2 "% g"
set yzeroaxis lt -2 lw 1.000
set y2zeroaxis lt -2 lw 1.000
set ytics border mirror norotate autofreq 
#unset ytics
set mytics default
set my2tics default
#set ylabel "Mean Recall of Unimodal System ( ~r{.4-}_{UM} )" 0,-0.5  font "Times-Roman,18"
#set ylabel "y-axis" 0,-0.5  font "Times-Roman,18"
set ylabel "Mean Time Req'd (given specified user model)"   font "Times-Roman,18"
#set ytics rotate by 0 (0,  .2, .4, .6, .8, 1)
#set yrange [ 0.000000 : 1.00000 ] noreverse nowriteback
set yrange [ * : * ] noreverse nowriteback
set y2label "" 0,0  font ""
set y2range [ * : * ] noreverse nowriteback  # (currently [-10.0000:10.0000] )
set noy2tics
########################################
### Z AXIS
# Using `splot`, one can adjust the relative height of the vertical
# (Z) axis using ticslevel. The numeric argument provided specifies
# the location of the bottom of the scale (as a fraction of the
# z-range) above the xy-plane. The default value is 0.5. Negative
# values are permitted, but tic labels on the three axes may overlap.
set ticslevel 0
set zdata
set format z "% g"
set ztics 0,0.1,0.45
set zlabel "{/Symbol f}_{}" -6,-12.5  font "Times-Roman,18"
set zrange [ 0 : 0.45 ] noreverse nowriteback 
set ztics border nomirror norotate autofreq 
########################################
### TITLE OPTIONS
#set title "{/Symbol f}  Shown as a Function of Mean Recall of Unimodal System and {/Symbol l}" font "Times-Roman,20"
set title 	"Relationship of Two Cost Characterizations\n\
	for various TCFs" font "Times-Roman,20"
########################################
### ADDITIONAL OPTIONS
#set label "0.095" at screen 0.2,0.47 l rotate by 30
#set label "M=6" font "Times-Roman,18" at screen 0.5,0.9 c
#set label "0.095" at screen 0.78,0.43 l
#call 'Labels_Circles123.plt'
#call 'Labels_Circles123_Simplified.plt'

########################################
### THE ACTUAL PLOTTING
set datafile separator whitespace
# default view is 60 rot_x, 30 rot_z, 1 scale, 1 scale_z
#set view 60, 250, 0.9, 1.2
unset view
set view 0, 0, 1, 1
load	'labels_scatter3.plt'
plot	'keyboardVariants.Huffman_k2.dat' using 4:7 title "k2,U1" with points ls 1, \
	'keyboardVariants.Huffman_k7.dat' using 4:7 title  "k7,U1" with points ls 2, \
	'keyboardVariants.Huffman_k8.dat' using 4:7 title  "k8,U1" with points ls 3, \
	'keyboardVariants.Huffman_k10.dat' using 4:7 title  "k10,U1" with points ls 4, \
	'keyboardVariants.RowColModLexicographic.dat' using 4:7 title  "RCL,U1" with points ls 7, \
	'keyboardVariants.RowColModQWERTY.dat' using 4:7 title  "RCQ,U1" with points ls 6, \
	'keyboardVariants.RowColUnigram.dat' using 4:7 title  "RCU,U1" with points ls 5,\
	'keyboardVariants.LinearUnigram.dat' using 4:7 title  "LU,U1" with points ls 10

#	'keyboardVariants.LinearModLexicographic.dat' using 4:7 title  "LL,U1" with points ls 8,\
#	'keyboardVariants.LinearModQWERTY.dat' using 4:7 title  "LQ,U1" with points ls 9,\

#, \
#	'Circles.dat' with lines

#splot 	'BaseCircle.dat' with lines, 
#	'test.dat' with lines
	
# 	'output-PhiValuesSurface-(M=6).dat' with lines ls 1, \
#	'yz-plane-at-x=1.25.dat' with lines ls 1, \
#	'lineShowingZValue-3a.dat' with lines ls 1, \
#	'output-PhiValuesCurve-(6+1.09)-modify3D.dat' with lines ls 2
set output
#show view
#set terminal windows "Times-Roman" 10
#replot
#    EOF
