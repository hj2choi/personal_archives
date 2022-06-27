Module modImage

    Public Function Edge(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer, ByVal strength As Integer, ByVal negate As Boolean) As Color
        ' *** add your code here
        Dim red As Integer
        Dim green As Integer
        Dim blue As Integer

        ' Apply edge on the pixel using a simple edge kernel here
        '
        '  -1 -1  -1
        ' -1  8 -1
        '  -1 -1  -1

        red = strength * (image.GetPixel(x, y).R * 8)
        red += strength * (-image.GetPixel(Math.Max(x - 1, 0), y).R - image.GetPixel(Math.Min(x + 1, image.Width - 1), y).R - image.GetPixel(x, Math.Max(y - 1, 0)).R - image.GetPixel(x, Math.Min(y + 1, image.Height - 1)).R)
        red += strength * (-image.GetPixel(Math.Max(x - 1, 0), Math.Max(y - 1, 0)).R - image.GetPixel(Math.Min(x + 1, image.Width - 1), Math.Min(y + 1, image.Height - 1)).R - image.GetPixel(Math.Min(x + 1, image.Width - 1), Math.Max(y - 1, 0)).R - image.GetPixel(Math.Max(x - 1, 0), Math.Min(y + 1, image.Height - 1)).R)

        green = strength * (image.GetPixel(x, y).G * 8)
        green += strength * (-image.GetPixel(Math.Max(x - 1, 0), y).G - image.GetPixel(Math.Min(x + 1, image.Width - 1), y).G - image.GetPixel(x, Math.Max(y - 1, 0)).G - image.GetPixel(x, Math.Min(y + 1, image.Height - 1)).G)
        green += strength * (-image.GetPixel(Math.Max(x - 1, 0), Math.Max(y - 1, 0)).G - image.GetPixel(Math.Min(x + 1, image.Width - 1), Math.Min(y + 1, image.Height - 1)).G - image.GetPixel(Math.Min(x + 1, image.Width - 1), Math.Max(y - 1, 0)).G - image.GetPixel(Math.Max(x - 1, 0), Math.Min(y + 1, image.Height - 1)).G)


        blue = strength * (image.GetPixel(x, y).B * 8)
        blue += strength * (-image.GetPixel(Math.Max(x - 1, 0), y).B - image.GetPixel(Math.Min(x + 1, image.Width - 1), y).B - image.GetPixel(x, Math.Max(y - 1, 0)).B - image.GetPixel(x, Math.Min(y + 1, image.Height - 1)).B)
        blue += strength * (-image.GetPixel(Math.Max(x - 1, 0), Math.Max(y - 1, 0)).B - image.GetPixel(Math.Min(x + 1, image.Width - 1), Math.Min(y + 1, image.Height - 1)).B - image.GetPixel(Math.Min(x + 1, image.Width - 1), Math.Max(y - 1, 0)).B - image.GetPixel(Math.Max(x - 1, 0), Math.Min(y + 1, image.Height - 1)).B)

        Dim retColor As Color = Color.FromArgb(Math.Min(Math.Max(red, 0), 255), Math.Min(Math.Max(green, 0), 255), Math.Min(Math.Max(blue, 0), 255))
        If CInt(retColor.R) + CInt(retColor.G) + CInt(retColor.B) > 300 Then
            Dim light As Integer = Math.Max(Math.Max(CInt(retColor.R), CInt(retColor.G)), CInt(retColor.B))
            retColor = Color.FromArgb(light, light, light)
        Else
            retColor = Color.Black
        End If
        If negate = True Then
            retColor = Color.FromArgb(255 - retColor.R, 255 - retColor.G, 255 - retColor.B)
        End If
        Return retColor
    End Function


    ' This function applies roughen on a pixel using a deviation from 0 to 1
    Public Function Roughen(ByVal color As Color, ByVal deviation As Double, ByVal changeHue As Boolean, ByVal changeSaturation As Boolean, ByVal changeValue As Boolean) As Color
        Dim red As Integer = color.R
        Dim green As Integer = color.G
        Dim blue As Integer = color.B
        Dim hue As Double
        Dim saturation As Double
        Dim value As Double


        ' Adjust the V component of the colour using the deviation here
        RGB2HSV(red, green, blue, hue, saturation, value)

        Dim displacementFactor As Double = (deviation * 2) * (Rnd() - 0.5)

        ' Adjust the V component of the colour using the deviation here
        If changeHue Then
            hue = hue + displacementFactor * 360
            If hue < 0 Then
                hue = 360 - hue
            End If
            hue = hue Mod 360
        End If

        If changeSaturation Then
            saturation = Math.Max(Math.Min(saturation + displacementFactor, 1), 0)
        End If

        If changeValue Then
            value = Math.Max(Math.Min(value + displacementFactor, 1), 0)
        End If


        HSV2RGB(hue, saturation, value, red, green, blue)

        Return color.FromArgb(red, green, blue)
    End Function

    ' This function applies melt on an input image
    Public Sub Melt(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle, _
                    ByVal useSine As Boolean, ByVal amplitude As Integer, ByVal cycle As Integer, _
                    ByVal useRandom As Boolean, ByVal offset As Integer, ByVal period As Integer, ByVal angle As Integer)
        Dim displacement(0 To rect.Width) As Integer
        Dim randDisplacement(0 To rect.Width) As Integer

        Dim tempImage As Bitmap = input.Clone()

        ' rotate the area before processing
        Dim maxsize As Double = Math.Ceiling(Math.Sqrt(rect.Width ^ 2 + rect.Height ^ 2))
        Dim centerX As Integer = rect.X + rect.Width / 2.0
        Dim centerY As Integer = rect.Y + rect.Height / 2.0
        For x As Integer = centerX - maxsize / 2 To centerX + maxsize / 2
            For y As Integer = centerY - maxsize / 2 To centerY + maxsize / 2
                If 0 <= x And x < input.Width And 0 <= y And y < input.Height Then
                    Dim distX As Double = (x - centerX)
                    Dim distY As Double = (y - centerY)
                    Dim sourceX As Integer = Math.Cos((angle * Math.PI) / 180) * distX + Math.Sin((angle * Math.PI) / 180) * distY + centerX
                    Dim sourceY As Integer = -Math.Sin((angle * Math.PI) / 180) * distX + Math.Cos((angle * Math.PI) / 180) * distY + centerY

                    tempImage.SetPixel(x, y, GetPixel(input, sourceX, sourceY))

                End If
            Next
        Next

        Dim tempImage2 As Bitmap = tempImage.Clone()

        ' Apply melt to the selected area here
        If useSine Then
            For i As Integer = 0 To rect.Width - 1
                displacement(i) = Math.Abs(amplitude * Math.Sin(2 * Math.PI * cycle * (i / rect.Width)))
            Next
        End If
        If useRandom Then
            'Reset values
            Dim currentOffset As Integer = 0

            'A loop to go through each column from left to right
            For x As Integer = 0 To rect.Width - 1
                'Period is a random number from 1 to maximum value set by slider
                Dim currentPeriod As Integer = 1 + Rnd() * (period - 1)
                Dim sign As Integer = 1
                'Sign is a random choice, either -1 (down) or 1 (up)
                If Rnd() >= 0.5 Then
                    sign = 1
                Else
                    sign = -1
                End If

                'A loop to generate the displacements for the current period
                For i As Integer = x To x + currentPeriod - 1
                    If i >= rect.Width - 1 Then
                        Exit For
                    End If
                    'For each column i, create the random offset
                    currentOffset = currentOffset + sign * Rnd() * offset
                    If currentOffset < 0 Then
                        currentOffset = -1 * currentOffset
                        sign = -1 * sign
                    End If
                    randDisplacement(i) = currentOffset
                Next

                x = x + currentPeriod - 1
            Next
        End If
        ' Main processing
        For i As Integer = 0 + rect.X To rect.Width - 1 + rect.X
            For j As Integer = 0 + rect.Y To rect.Height - 1 + rect.Y
                'Output to a new image
                tempImage2.SetPixel(i, j, tempImage.GetPixel(i, Math.Min(Math.Max(j - displacement(i - rect.X) - randDisplacement(i - rect.X), 0), input.Height - 1)))
            Next j
        Next i

        input = tempImage2.Clone()

        ' rotate back to original image
        maxsize = Math.Ceiling(Math.Sqrt(rect.Width ^ 2 + rect.Height ^ 2))
        centerX = rect.X + rect.Width / 2.0
        centerY = rect.Y + rect.Height / 2.0
        For x As Integer = centerX - maxsize / 2 To centerX + maxsize / 2
            For y As Integer = centerY - maxsize / 2 To centerY + maxsize / 2
                If 0 <= x And x < input.Width And 0 <= y And y < input.Height Then
                    Dim distX As Double = (x - centerX)
                    Dim distY As Double = (y - centerY)
                    Dim sourceX As Integer = Math.Cos((-angle * Math.PI) / 180) * distX + Math.Sin((-angle * Math.PI) / 180) * distY + centerX
                    Dim sourceY As Integer = -Math.Sin((-angle * Math.PI) / 180) * distX + Math.Cos((-angle * Math.PI) / 180) * distY + centerY

                    input.SetPixel(x, y, GetPixel(tempImage2, sourceX, sourceY))

                End If
            Next
        Next

        'insert into output image
        For x As Integer = rect.X To rect.Right
            For y As Integer = rect.Y To rect.Bottom
                output.SetPixel(x, y, GetPixel(input, x, y))
            Next
        Next
    End Sub

    ' This function applies ripple on an input image
    Public Sub Ripple(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle, ByVal amplitude As Integer, ByVal frequency As Integer)

        Dim centerX As Integer = rect.X + rect.Width / 2.0
        Dim centerY As Integer = rect.Y + rect.Height / 2.0
        Dim diag As Double = Math.Sqrt(rect.Width ^ 2 + rect.Height ^ 2) / 4

        For x As Integer = rect.Left To rect.Right
            For y As Integer = rect.Top To rect.Bottom

                Dim radius As Double = Math.Sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y))
                Dim angle As Double = Math.Atan2(y - centerY, x - centerX)

                Dim displacement As Double = amplitude * Math.Sin(2 * Math.PI * (radius / diag) * frequency)
                radius += displacement

                Dim newX As Integer = radius * Math.Cos(angle) + centerX
                Dim newY As Integer = radius * Math.Sin(angle) + centerY

                output.SetPixel(x, y, GetPixel(input, Math.Min(Math.Max(newX, 0), input.Width - 1), Math.Min(Math.Max(newY, 0), input.Height - 1)))
            Next
        Next

    End Sub

    ' This function applies rotation on an input image
    Public Sub Rotate(ByRef input As Bitmap, ByRef output As Bitmap, ByRef rect As Rectangle, ByVal angle As Integer)


        ' Apply rotation to the selected area here
        Dim maxsize As Double = Math.Ceiling(Math.Sqrt(rect.Width ^ 2 + rect.Height ^ 2))
        Dim centerX As Integer = rect.X + rect.Width / 2.0
        Dim centerY As Integer = rect.Y + rect.Height / 2.0
        For x As Integer = centerX - maxsize / 2 To centerX + maxsize / 2
            For y As Integer = centerY - maxsize / 2 To centerY + maxsize / 2
                If 0 <= x And x < input.Width And 0 <= y And y < input.Height Then
                    Dim distX As Double = (x - centerX)
                    Dim distY As Double = (y - centerY)
                    Dim sourceX As Integer = Math.Cos((angle * Math.PI) / 180) * distX + Math.Sin((angle * Math.PI) / 180) * distY + centerX
                    Dim sourceY As Integer = -Math.Sin((angle * Math.PI) / 180) * distX + Math.Cos((angle * Math.PI) / 180) * distY + centerY

                    If ((rect.X <= sourceX And sourceX <= rect.X + rect.Width) And (rect.Y <= sourceY And sourceY <= rect.Y + rect.Height)) Then

                        output.SetPixel(x, y, GetPixel(input, sourceX, sourceY))

                        Interpolation(output, x, y)
                    ElseIf ((rect.X <= x And x <= rect.X + rect.Width) And (rect.Y <= y And y <= rect.Y + rect.Height)) Then
                        output.SetPixel(x, y, Color.Black)

                    End If
                End If
            Next
        Next
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

