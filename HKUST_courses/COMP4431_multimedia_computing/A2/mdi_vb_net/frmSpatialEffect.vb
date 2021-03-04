Public Class frmSpatialEffect
    Inherits System.Windows.Forms.Form

    Sub ApplyEffect()
        If Not DirectCast(Me.MdiParent, frmMain).selectedImageForm Is Nothing Then
            Dim selectedImageForm As frmImage = DirectCast(Me.MdiParent, frmMain).selectedImageForm

            If selectedImageForm.selectedArea.IsEmpty() Then
                MsgBox("Please select an area for application of the selected filter")
                Return
            End If

            Dim buffer As Bitmap = selectedImageForm.image.Clone()

            selectedImageForm.RemoveDashRect()

            Process(selectedImageForm.image, buffer, selectedImageForm.selectedArea)

            selectedImageForm.SetPicture(buffer.Clone())
            selectedImageForm.DrawDashRect()
        End If
    End Sub

    Overridable Sub Process(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle)

    End Sub
End Class
