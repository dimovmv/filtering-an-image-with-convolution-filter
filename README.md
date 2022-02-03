# filtering-an-image-with-convolution-filter
Wellcome to my project
Java application for filtering an image with convolution filter

Introduction: 
This is a command line menu - driven Java application for filtering a PNG images and/or URL using a convolution kernel.

Details:
The application process only PNG files, it does not work with other file formats (Only in case when the user choose an image from his computer).
The application also works with URL. Instead of a file name and directory, the user can insert a link to an image from internet and it will be processed too.
It uses a 2D array structure to implement the filtering.

How to work with the application:

When the program is started the menu shows five options:
1) Display Filters Available
2) Enter Filter Name
3) Enter Image Name
4) Enter URL
5) Quit

1. When user press 1 and "Enter", the program will display all available filters (kernels). The user can choose from: IDENTITY, EDGE_DETECTION_1, EDGE_DETECTION_2, LAPLACIAN, SHARPEN, VERTICAL_LINES, HORIZONTAL_LINES, DIAGONAL_45_LINES, BOX_BLUR, SOBEL_HORIZONTAL or SOBEL_VERTICAL filters.

2. After pressing 2 and "Enter", the user have to type the name of the selected filter.  This is not a case sensitive, which means that the name of the filter can be written with capital or non capital letters. After typing the name of the selected filter, the user have to press "Enter". Immediately the program will show a message with the name of the filter selected.
The default filter is IDENTITY. That means is the user does not select any filter, the filter IDENTITY will be applied automaticaly and the output image should look the same as the input image.

3. By pressing 3 and "Enter" the user will be asked to enter a file name.  It is important the file name to be written correctly, other wise an error message will appear. It is mandatory the file to be in PNG format. 
Next step is the program to ask for a directory where to search that file. (Making the application I've always used the project's root directory and it is enough just to type ".\"(for Windows user) or "./" for others. 
If the file  does not exist in this directory - an error message will appear.
If the file name is writen correctly and the file exists in the directory specified, a message "Retrieving file.."
will be shown. Right after that another message "Processing... please wait" will appear and the processed image will be writen in the same directory. The name of the new file should be "output". It again will be in PNG format.

4. Pressing 4 and "Enter" gives the user the opportunity to select an image from internet, not from his device. The program will ask for URL. After entering the URL, the user must press "Enter". In case of a problem with url - an error message will appear. If everything is OK, should happen the same as in the previous point.

5.  Pressing 5 + "Enter" will terminate the program. A information message "Shutting down... Please wait" will appear.

What will be happen if the user first enter an image name or url?
In these cases the program will process the images with the default filter (IDENTITY) and the output will be the same image as an input image.


References:
For this project I have used different sources of information. First of all were the lectures and the discusion forum. After that are different on-line sources:

https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
https://en.wikipedia.org/wiki/Kernel_(image_processing)
https://stackoverflow.com/questions/29226704/image-getraster-getdatabuffer-returns-array-of-negative-values
http://www.jhlabs.com/ip/blurring.html
https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
https://github.com/RyanClinton777/ImageFilter


