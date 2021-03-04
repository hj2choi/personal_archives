Public Class frmHistogram
    Inherits frmEffect

#Region " Windows Form Designer generated code "

    Public Sub New()
        MyBase.New()

        'This call is required by the Windows Form Designer.
        InitializeComponent()

        'Add any initialization after the InitializeComponent() call

    End Sub

    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    Friend WithEvents btnShow As System.Windows.Forms.Button
    Friend WithEvents lblPlanes As System.Windows.Forms.Label
    Friend WithEvents cboPlane As System.Windows.Forms.ComboBox
    Friend WithEvents btnAutoContrast As System.Windows.Forms.Button
    Friend WithEvents txtValueStat As System.Windows.Forms.Label
    Friend WithEvents txtTargetValue As System.Windows.Forms.Label
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents txtTargetStat As System.Windows.Forms.Label
    Friend WithEvents picHistogram As System.Windows.Forms.PictureBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.txtTargetValue = New System.Windows.Forms.Label()
        Me.txtValueStat = New System.Windows.Forms.Label()
        Me.btnAutoContrast = New System.Windows.Forms.Button()
        Me.lblPlanes = New System.Windows.Forms.Label()
        Me.cboPlane = New System.Windows.Forms.ComboBox()
        Me.picHistogram = New System.Windows.Forms.PictureBox()
        Me.btnShow = New System.Windows.Forms.Button()
        Me.txtTargetStat = New System.Windows.Forms.Label()
        CType(Me.picHistogram, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(345, 52)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(178, 17)
        Me.Label1.TabIndex = 16
        Me.Label1.Text = "autoContrast methodology:"
        '
        'txtTargetValue
        '
        Me.txtTargetValue.AutoSize = True
        Me.txtTargetValue.Location = New System.Drawing.Point(345, 113)
        Me.txtTargetValue.Name = "txtTargetValue"
        Me.txtTargetValue.Size = New System.Drawing.Size(230, 17)
        Me.txtTargetValue.TabIndex = 15
        Me.txtTargetValue.Text = "target inter90%range = 150+St.dev"
        '
        'txtValueStat
        '
        Me.txtValueStat.AutoSize = True
        Me.txtValueStat.Location = New System.Drawing.Point(17, 249)
        Me.txtValueStat.Name = "txtValueStat"
        Me.txtValueStat.Size = New System.Drawing.Size(199, 17)
        Me.txtValueStat.TabIndex = 14
        Me.txtValueStat.Text = "Value => Mean = 0, St.dev = 0"
        '
        'btnAutoContrast
        '
        Me.btnAutoContrast.Location = New System.Drawing.Point(130, 213)
        Me.btnAutoContrast.Name = "btnAutoContrast"
        Me.btnAutoContrast.Size = New System.Drawing.Size(107, 29)
        Me.btnAutoContrast.TabIndex = 13
        Me.btnAutoContrast.Text = "AutoContrast"
        Me.btnAutoContrast.UseVisualStyleBackColor = True
        '
        'lblPlanes
        '
        Me.lblPlanes.AutoSize = True
        Me.lblPlanes.Location = New System.Drawing.Point(14, 17)
        Me.lblPlanes.Name = "lblPlanes"
        Me.lblPlanes.Size = New System.Drawing.Size(92, 17)
        Me.lblPlanes.TabIndex = 12
        Me.lblPlanes.Text = "Histogram of:"
        '
        'cboPlane
        '
        Me.cboPlane.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cboPlane.FormattingEnabled = True
        Me.cboPlane.Items.AddRange(New Object() {"Value", "Red", "Green", "Blue"})
        Me.cboPlane.Location = New System.Drawing.Point(104, 14)
        Me.cboPlane.Name = "cboPlane"
        Me.cboPlane.Size = New System.Drawing.Size(234, 24)
        Me.cboPlane.TabIndex = 11
        '
        'picHistogram
        '
        Me.picHistogram.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.picHistogram.Location = New System.Drawing.Point(14, 52)
        Me.picHistogram.Name = "picHistogram"
        Me.picHistogram.Size = New System.Drawing.Size(324, 148)
        Me.picHistogram.TabIndex = 10
        Me.picHistogram.TabStop = False
        '
        'btnShow
        '
        Me.btnShow.Location = New System.Drawing.Point(14, 213)
        Me.btnShow.Name = "btnShow"
        Me.btnShow.Size = New System.Drawing.Size(96, 29)
        Me.btnShow.TabIndex = 8
        Me.btnShow.Text = "Show"
        '
        'txtTargetStat
        '
        Me.txtTargetStat.AutoSize = True
        Me.txtTargetStat.Location = New System.Drawing.Point(345, 82)
        Me.txtTargetStat.Name = "txtTargetStat"
        Me.txtTargetStat.Size = New System.Drawing.Size(213, 17)
        Me.txtTargetStat.TabIndex = 17
        Me.txtTargetStat.Text = "target median = 2mean - median"
        '
        'frmHistogram
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(595, 286)
        Me.Controls.Add(Me.txtTargetStat)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.txtTargetValue)
        Me.Controls.Add(Me.txtValueStat)
        Me.Controls.Add(Me.btnAutoContrast)
        Me.Controls.Add(Me.lblPlanes)
        Me.Controls.Add(Me.cboPlane)
        Me.Controls.Add(Me.picHistogram)
        Me.Controls.Add(Me.btnShow)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmHistogram"
        Me.Text = "Histogram"
        CType(Me.picHistogram, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

#End Region

    Private Histogram(0 To 255) As Integer
    Private valueAverage As Double
    Private valueStDev As Double
    Private valueMedian As Double
    Private percentile5 As Double
    Private percentile95 As Double

    Private Sub frmHistogram_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        cboPlane.SelectedIndex = 0
    End Sub
    

    Private Sub btnShow_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnShow.Click

        ' Clear the histogram using Array.Clear here
        Array.Clear(Histogram, 0, 255)
        ApplyEffect()
        If cboPlane.SelectedIndex = 0 Then
            Dim selectedImageForm As frmImage = DirectCast(Me.MdiParent, frmMain).selectedImageForm
            If selectedImageForm.selectedArea.IsEmpty() Then
                Return
            End If
            computeValueStatistics(selectedImageForm.image)
        End If
        picHistogram.Refresh()

    End Sub

    Private Sub reScale(ByRef originalColor As Integer, ByVal median As Integer, ByVal targetStDev As Double, ByVal originalStDev As Double)
        Dim deviationFromMedian As Integer = median - originalColor
        deviationFromMedian *= (targetStDev / originalStDev)
        originalColor = valueAverage + (median - valueAverage) - deviationFromMedian


    End Sub

    Private Sub computeValueStatistics(ByRef image As Bitmap)
        Array.Clear(Histogram, 0, 255)
        Dim valueSum As Double = 0

        Dim Value As Integer    ' value *255
        For i As Integer = 0 To image.Height - 1
            For j As Integer = 0 To image.Width - 1
                Dim color As Color = image.GetPixel(j, i)
                Value = Value = Math.Max(color.R, color.G)
                Value = Math.Max(color.G, color.B)
                valueSum += Value
                Histogram(Value) += 1
            Next
        Next
        valueAverage = valueSum / (image.Height * image.Width)
        valueSum = 0
        For i As Integer = 0 To image.Height - 1
            For j As Integer = 0 To image.Width - 1
                Dim color As Color = image.GetPixel(j, i)
                Value = Value = Math.Max(color.R, color.G)
                Value = Math.Max(color.G, color.B)
                valueSum += (valueAverage - Value) * (valueAverage - Value)
            Next
        Next
        valueStDev = Math.Sqrt(valueSum / (image.Height * image.Width))

        percentile5 = 0
        percentile95 = 0
        valueMedian = 0
        Dim pixelCount As Integer = 0
        For i As Integer = 0 To 255
            pixelCount += Histogram(i)
            If percentile5 = 0 And pixelCount > (image.Height * image.Width) * 0.05 Then
                percentile5 = i
            End If
            If valueMedian = 0 And pixelCount > (image.Height * image.Width) * 0.5 Then
                valueMedian = i
            End If
            If percentile95 = 0 And pixelCount > (image.Height * image.Width) * 0.95 Then
                percentile95 = i
            End If

        Next

        txtValueStat.Text = "Value => Mean = " + CInt(valueAverage).ToString() + ", Median = " + CInt(valueMedian).ToString + ", St.Dev = " + CInt(valueStDev).ToString() + ", per5% = " + CInt(percentile5).ToString + ", per95% = " + CInt(percentile95).ToString
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Dim color As Color = image.GetPixel(x, y)


        ' Count the histogram using the selected colour plane here
        ' Note: there are 256 boxes in the histogram
        If cboPlane.SelectedIndex = 0 Then
            Dim value As Integer
            value = Math.Max(color.R, color.G)
            value = Math.Max(color.G, color.B)
            Histogram(value) += 1
        End If
        If cboPlane.SelectedIndex = 1 Then
            Histogram(color.R) += 1
        End If
        If cboPlane.SelectedIndex = 2 Then
            Histogram(color.G) += 2
        End If
        If cboPlane.SelectedIndex = 3 Then
            Histogram(color.B) += 3
        End If

        Return color
    End Function

    Private Sub picHistogram_Paint(ByVal sender As Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles picHistogram.Paint
        Dim g As Graphics = e.Graphics()
        g.Clear(Color.White)

        Dim max As Integer = 0
        Dim i As Integer
        For i = 0 To 255
            If Histogram(i) > max Then
                max = Histogram(i)
            End If
        Next

        If max > 0 Then
            Dim x As Integer, y As Integer
            For x = 0 To picHistogram.ClientRectangle.Width - 1
                y = (1.0# - Histogram(x / (picHistogram.ClientRectangle.Width - 1) * 255) / max) * (picHistogram.ClientRectangle.Height - 1)
                g.DrawLine(Pens.Black, x, picHistogram.ClientRectangle.Height - 1, x, y)
            Next
        End If
    End Sub

    Private Sub btnAutoContrast_Click(sender As Object, e As EventArgs) Handles btnAutoContrast.Click
        
        Dim selectedImageForm As frmImage = DirectCast(Me.MdiParent, frmMain).selectedImageForm

        If selectedImageForm.selectedArea.IsEmpty() Then
            MsgBox("Please select an area for application of the selected filter")
            Return
        End If

        Dim buffer As Bitmap = selectedImageForm.image.Clone()

        selectedImageForm.RemoveDashRect()

        Dim color As Color
        Dim inter90percentRange As Integer = percentile95 - percentile5
        For y As Integer = selectedImageForm.selectedArea.Top To selectedImageForm.selectedArea.Bottom
            For x As Integer = selectedImageForm.selectedArea.Left To selectedImageForm.selectedArea.Right
                'color = Process(selectedImageForm.image, x, y)
                color = buffer.GetPixel(x, y)
                Dim r As Integer = color.R
                Dim g As Integer = color.G
                Dim b As Integer = color.B
                'r += (123 - valueMedian)
                'g += (123 - valueMedian)
                'b += (123 - valueMedian)
                'reScale(r, valueAverage, 35, valueStDev)
                'reScale(g, valueAverage, 35, valueStDev)
                'reScale(b, valueAverage, 35, valueStDev)
                reScale(r, valueMedian, 123 + 2 * valueStDev, inter90percentRange)
                reScale(g, valueMedian, 123 + 2 * valueStDev, inter90percentRange)
                reScale(b, valueMedian, 123 + 2 * valueStDev, inter90percentRange)
                Dim setColor As Color = color.FromArgb(Math.Min(Math.Max(r, 0), 255), Math.Min(Math.Max(g, 0), 255), Math.Min(Math.Max(b, 0), 255))
                'txtValueStat.Text = x.ToString + ", " + y.ToString
                
                buffer.SetPixel(x, y, setColor)
            Next
        Next
        txtTargetStat.Text = "target mean = " + CInt(2 * valueAverage - valueMedian).ToString
        txtTargetValue.Text = "target inter90%range = " + (CInt(150 + valueStDev)).ToString
        selectedImageForm.SetPicture(buffer.Clone())
        selectedImageForm.DrawDashRect()
        btnShow.PerformClick()
    End Sub
End Class
