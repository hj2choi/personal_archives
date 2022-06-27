Module modImage

    Public Sub Edge(ByRef fImageForm As frmImage, ByVal strength As Integer)
        ' *** add your code here
    End Sub


    ' This function applies roughen on a pixel using a deviation from 0 to 1
    Public Function Roughen(ByVal color As Color, ByVal deviation As Double) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B
        Dim hue As Double
        Dim saturation As Double
        Dim value As Double


        ' Adjust the V component of the colour using the deviation here


        Return color.FromArgb(red, green, blue)
    End Function

    ' This function applies melt on an input image
    Public Sub Melt(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle, _
                    ByVal useSine As Boolean, ByVal amplitude As Integer, ByVal cycle As Integer, _
                    ByVal useRandom As Boolean, ByVal offset As Integer, ByVal period As Integer)
        Dim displacement(0 To rect.Width) As Integer


        ' Apply melt to the selected area here


    End Sub

    ' This function applies rotation on an input image
    Public Sub Rotate(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle, ByVal angle As Integer)


        ' Apply rotation to the selected area here


    End Sub


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

    ' This function returns the interpolation of four pixels around an x and y position
    Private Function Interpolation(ByRef image As Bitmap, ByVal x As Double, ByVal y As Double) As Color
        Dim bx As Integer = Math.Floor(x)
        Dim by As Integer = Math.Floor(y)
        Dim a As Double, b As Double, a0 As Double, a1 As Double, a2 As Double, a3 As Double
        Dim red As Double, green As Double, blue As Double

        a = x - bx
        b = y - by

        a0 = (1 - a) * (1 - b)
        a1 = a * (1 - b)
        a2 = (1 - a) * b
        a3 = a * b

        red = GetPixel(image, bx, by).R * a0 + GetPixel(image, bx + 1, by).R * a1 + GetPixel(image, bx, by + 1).R * a2 + GetPixel(image, bx + 1, by + 1).R * a3
        green = GetPixel(image, bx, by).G * a0 + GetPixel(image, bx + 1, by).G * a1 + GetPixel(image, bx, by + 1).G * a2 + GetPixel(image, bx + 1, by + 1).G * a3
        blue = GetPixel(image, bx, by).B * a0 + GetPixel(image, bx + 1, by).B * a1 + GetPixel(image, bx, by + 1).B * a2 + GetPixel(image, bx + 1, by + 1).B * a3

        Return Color.FromArgb(red, green, blue)
    End Function

End Module

