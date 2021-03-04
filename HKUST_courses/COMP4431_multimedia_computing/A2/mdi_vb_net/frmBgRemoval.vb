Public Class frmBgRemoval
    Inherits frmEffect


    Public Class Pixel
        Public x As Integer
        Public y As Integer
        Public color As Color
        Public Sub New(xx As Integer, yy As Integer, cc As Color)
            x = xx
            y = yy
            color = cc
        End Sub

    End Class


    Private selectedImageForm As frmImage
    Private pixelFilled(2, 2) As Boolean

    Private Sub rbtn4Dir_CheckedChanged(sender As Object, e As EventArgs) Handles rbtn4Dir.CheckedChanged

    End Sub

    Private Sub btnApply_Click(sender As Object, e As EventArgs) Handles btnApply.Click
        If (Not IsNumeric(tboxX.Text)) Or (Not IsNumeric(tboxY.Text)) Then
            Return
        End If
        Dim initialX As Integer = Math.Min(Math.Max(Val(tboxX.Text), 0), 255)
        Dim initialY As Integer = Math.Min(Math.Max(Val(tboxY.Text), 0), 255)

        If Not DirectCast(Me.MdiParent, frmMain).selectedImageForm Is Nothing Then
            selectedImageForm = DirectCast(Me.MdiParent, frmMain).selectedImageForm

            If selectedImageForm.selectedArea.IsEmpty() Then
                MsgBox("Please select an area for application of the selected filter")
                Return
            End If
            ReDim pixelFilled(selectedImageForm.image.Height, selectedImageForm.image.Width)
            Dim buffer As Bitmap = selectedImageForm.image.Clone()

            selectedImageForm.RemoveDashRect()

            Dim color As Color
            color = selectedImageForm.image.GetPixel(initialX, initialY)
            Dim pix As Pixel
            pix = New Pixel(initialX, initialY, color)

            'floodFill(buffer, initialX, initialY, color)
            floodFill(buffer, pix)
            'For y As Integer = selectedImageForm.selectedArea.Top To selectedImageForm.selectedArea.Bottom
            'For x As Integer = selectedImageForm.selectedArea.Left To selectedImageForm.selectedArea.Right
            'color = Process(selectedImageForm.image, x, y)
            'buffer.SetPixel(x, y, color)
            'Next
            'Next

            selectedImageForm.SetPicture(buffer.Clone())
            selectedImageForm.DrawDashRect()
        End If
    End Sub
    Private Sub floodFill(ByRef image As Bitmap, ByVal ptCell As Pixel)
        Dim queueCells As New System.Collections.Generic.Queue(Of Pixel)
        queueCells.Enqueue(ptCell)
        While queueCells.Count > 0
            ptCell = queueCells.Dequeue()
            If (ptCell.x >= 0 And ptCell.x < image.Width) Then

                If (ptCell.y >= 0 And ptCell.y < image.Height) Then

                    Dim newColor As Color = image.GetPixel(ptCell.x, ptCell.y)
                    Dim diff As Integer = Math.Abs(CInt(newColor.R) - CInt(ptCell.color.R)) + Math.Abs(CInt(newColor.G) - CInt(ptCell.color.G)) + Math.Abs(CInt(newColor.B) - CInt(ptCell.color.B))

                    If (pixelFilled(ptCell.y, ptCell.x) = False And diff < tbarThreshold.Value) Then
                        'Me.m_oColor = Color.FromArgb((((Me.m_oColor.ToArgb And &HFFFFFF) + 140) And &HFFFFFF) Or &HFF000000)

                        '    m_oColor = Color.FromArgb((int)(((((uint)m_oColor.ToArgb() & 0xffffff) + 140) & 0xffffff) | 0xff000000));
                        'm_brushFill = New SolidBrush(m_oColor)
                        'set white color to target pixel
                        image.SetPixel(ptCell.x, ptCell.y, Color.White)
                        pixelFilled(ptCell.y, ptCell.x) = True

                        queueCells.Enqueue(New Pixel(ptCell.x - 1, ptCell.y, newColor))
                        queueCells.Enqueue(New Pixel(ptCell.x + 1, ptCell.y, newColor))
                        queueCells.Enqueue(New Pixel(ptCell.x, ptCell.y + 1, newColor))
                        queueCells.Enqueue(New Pixel(ptCell.x, ptCell.y - 1, newColor))
                        If rbtn8Dir.Checked = True Then
                            queueCells.Enqueue(New Pixel(ptCell.x - 1, ptCell.y - 1, newColor))
                            queueCells.Enqueue(New Pixel(ptCell.x + 1, ptCell.y + 1, newColor))
                            queueCells.Enqueue(New Pixel(ptCell.x - 1, ptCell.y + 1, newColor))
                            queueCells.Enqueue(New Pixel(ptCell.x + 1, ptCell.y - 1, newColor))
                        End If
                    End If
                End If
            End If
        End While
    End Sub
    Private Sub floodFill(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer, ByVal oldColor As Color)
        ' base case 1: return if x and y is out of bounds.
        If x >= image.Width Or x < 0 Or y < 0 Or y >= image.Height Then
            Return
        End If
        Dim newColor As Color = image.GetPixel(x, y)
        ' base case 2: return if reference color differ from color in x and y.
        Dim diff As Integer = Math.Abs(CInt(newColor.R) - CInt(oldColor.R)) + Math.Abs(CInt(newColor.G) - CInt(oldColor.G)) + Math.Abs(CInt(newColor.B) - CInt(oldColor.B))
        'Dim diff As Integer = newColor.R - oldColor.R
        If diff > tbarThreshold.Value Then
            Return
        End If
        ' base case 3: return if target point is already filled
        If pixelFilled(y, x) = True Then
            Return
        End If
        'set white color to target pixel
        image.SetPixel(x, y, Color.White)
        pixelFilled(y, x) = True

        'recursive call
        floodFill(image, x + 1, y, newColor)
        floodFill(image, x - 1, y, newColor)
        floodFill(image, x, y + 1, newColor)
        floodFill(image, x, y - 1, newColor)
    End Sub

End Class
