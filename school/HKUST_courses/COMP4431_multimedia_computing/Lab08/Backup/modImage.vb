Module modImage

    ' This function applies grayscale on a pixel using averaging RGB values
    Public Function Grayscale(ByVal color As Color) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B

        ' Convert the colour to a grayscale value here
        Dim value As Integer = (red + green + blue) / 3

        Return color.FromArgb(value, value, value)
    End Function

    ' This function applies blur on a pixel using a different size of kernel
    Public Function Blur(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer, ByVal size As Integer) As Color
        Dim red As Integer
        Dim green As Integer
        Dim blue As Integer


        ' Apply blur on the pixel based on the size here
        '
        ' size = 1 -> 3 x 3 blur kernel
        ' size = 2 -> 4 x 4 blur kernel
        ' size = 3 -> 5 x 5 blur kernel
        ' and so on


        Return Color.FromArgb(red, green, blue)
    End Function

    ' This function applies edge on a pixel
    Public Function Edge(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Dim red As Integer
        Dim green As Integer
        Dim blue As Integer


        ' Apply edge on the pixel using a simple edge kernel here
        '
        '  0 -1  0
        ' -1  4 -1
        '  0 -1  0


        Return Color.FromArgb(red, green, blue)
    End Function

    ' This function applies ghost on a pixel
    Public Function Ghost(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer, ByVal threshold As Integer) As Color


        ' Apply ghost on the pixel here
        '
        ' 1. Convert the image to grayscale (you can use the GrayScale function to do this)
        ' 2. Detect edge using the Sobel edge detector
        '
        '         +1  0 -1       +1 +2 +1
        '    Gx = +2  0 -2  Gy =  0  0  0
        '         +1  0 -1       -1 -2 -1
        '
        '    G = |Gx| + |Gy|
        '
        ' 3. Change the result to either black or white by comparing to the threshold

        Return Color.Black
    End Function


    Public Sub RGB2HSV(ByVal red As Integer, ByVal green As Integer, ByVal blue As Integer, ByRef h As Double, ByRef s As Double, ByRef v As Double)
        Dim max, min As Double
        Dim r As Double, g As Double, b As Double

        r = red / 255.0
        g = green / 255.0
        b = blue / 255.0
        'Convert RGB to [0,1]

        'Then get the max and min of r,g,b
        max = r
        If max < g Then max = g
        If max < b Then max = b
        min = r
        If min > g Then min = g
        If min > b Then min = b

        v = max 'this is value v

        'next calculate saturation, s 
        If max = 0 Then
            s = 0
        Else
            s = (max - min) / max
        End If

        ' The last step is hue, h
        If s = 0 Then
            h = -1
        Else
            If r = max Then
                h = (g - b) / (max - min)
            ElseIf g = max Then
                h = 2 + (b - r) / (max - min)
            ElseIf b = max Then
                h = 4 + (r - g) / (max - min)
            End If

            h = h * 60
            If h < 0 Then
                h = h + 360
            End If
        End If
    End Sub


    Private Sub HSV2RGB(ByVal h As Double, ByVal s As Double, ByVal v As Double, ByRef red As Integer, ByRef green As Integer, ByRef blue As Integer)
        Dim r As Double, g As Double, b As Double
        Dim i As Double, f As Double, p As Double, q As Double, t As Double

        If s = 0 Then
            r = v
            g = v
            b = v
        Else
            If h = 360 Then
                h = 0
            End If
            h = h / 60
            i = Int(h)

            f = h - i
            p = v * (1 - s)
            q = v * (1 - (s * f))
            t = v * (1 - (s * (1 - f)))

            Select Case i
                Case 0
                    r = v
                    g = t
                    b = p
                Case 1
                    r = q
                    g = v
                    b = p
                Case 2
                    r = p
                    g = v
                    b = t
                Case 3
                    r = p
                    g = q
                    b = v
                Case 4
                    r = t
                    g = p
                    b = v
                Case 5
                    r = v
                    g = p
                    b = q
            End Select
        End If

        red = CInt(r * 255.0)
        green = CInt(g * 255.0)
        blue = CInt(b * 255.0)

        red = ClipByte(red)
        green = ClipByte(green)
        blue = ClipByte(blue)
    End Sub

    ' This function clips a value into the range of 0 to 255
    Private Function ClipByte(ByRef value As Integer) As Integer
        If value > 255 Then
            Return 255
        ElseIf value < 0 Then
            Return 0
        End If
        Return value
    End Function

    ' This function clips a value into the range of 0 to 1
    Private Function ClipFloat(ByRef value As Double) As Double
        If value > 1 Then
            Return 1
        ElseIf value < 0 Then
            Return 0
        End If
        Return value
    End Function

    ' This function gets a pixel from the image without going out of bounds
    Private Function GetPixel(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        If x < 0 Then
            x = 0
        ElseIf x > image.Width - 1 Then
            x = image.Width - 1
        End If
        If y < 0 Then
            y = 0
        ElseIf y > image.Height - 1 Then
            y = image.Height - 1
        End If
        Return image.GetPixel(x, y)
    End Function
End Module

