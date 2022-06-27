Module modImage

    ' This function applies negation on a pixel using RGB planes (mode = 0) or HSV planes (mode = 1)
    Public Function Negation(ByVal color As Color, _
                             ByVal plane1 As Boolean, ByVal plane2 As Boolean, ByVal plane3 As Boolean, _
                             ByVal mode As Short) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B


        ' Apply the negation on the pixel here


        Return color.FromArgb(red, green, blue)
    End Function

    ' This function applies grayscale on a pixel using averaging RGB values
    Public Function Grayscale(ByVal color As Color) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B
        Dim value As Integer = 0


        ' Convert the colour to a grayscale value here


        Return color.FromArgb(value, value, value)
    End Function

    ' This function applies brightness on a pixel by adding/subtracting an offset to the RGB values
    Public Function Brightness(ByVal color As Color, ByVal offset As Integer) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B


        ' Change the pixel by applying the offset here
        ' Remember to handle clipping for each colour value


        Return color.FromArgb(red, green, blue)
    End Function

    ' This function applies posterization on a pixel with specific bitmasks for the RGB planes
    Public Function Posterization(ByVal color As Color, _
                                  ByVal redBits As Integer, ByVal greenBits As Integer, ByVal blueBits As Integer) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B


        ' Compute the bitmask for each plane and apply it on the colour here


        Return color.FromArgb(red, green, blue)
    End Function

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


    Private Sub RGB2HSV(ByVal red As Integer, ByVal green As Integer, ByVal blue As Integer, ByRef h As Double, ByRef s As Double, ByRef v As Double)
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
End Module

