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
    Friend WithEvents picHistogram As System.Windows.Forms.PictureBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnShow = New System.Windows.Forms.Button
        Me.picHistogram = New System.Windows.Forms.PictureBox
        Me.lblPlanes = New System.Windows.Forms.Label
        Me.cboPlane = New System.Windows.Forms.ComboBox
        CType(Me.picHistogram, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'btnShow
        '
        Me.btnShow.Location = New System.Drawing.Point(107, 185)
        Me.btnShow.Name = "btnShow"
        Me.btnShow.Size = New System.Drawing.Size(80, 25)
        Me.btnShow.TabIndex = 8
        Me.btnShow.Text = "Show"
        '
        'picHistogram
        '
        Me.picHistogram.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.picHistogram.Location = New System.Drawing.Point(12, 45)
        Me.picHistogram.Name = "picHistogram"
        Me.picHistogram.Size = New System.Drawing.Size(270, 128)
        Me.picHistogram.TabIndex = 10
        Me.picHistogram.TabStop = False
        '
        'lblPlanes
        '
        Me.lblPlanes.AutoSize = True
        Me.lblPlanes.Location = New System.Drawing.Point(12, 15)
        Me.lblPlanes.Name = "lblPlanes"
        Me.lblPlanes.Size = New System.Drawing.Size(69, 13)
        Me.lblPlanes.TabIndex = 12
        Me.lblPlanes.Text = "Histogram of:"
        '
        'cboPlane
        '
        Me.cboPlane.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cboPlane.FormattingEnabled = True
        Me.cboPlane.Items.AddRange(New Object() {"Value", "Red", "Green", "Blue"})
        Me.cboPlane.Location = New System.Drawing.Point(87, 12)
        Me.cboPlane.Name = "cboPlane"
        Me.cboPlane.Size = New System.Drawing.Size(195, 21)
        Me.cboPlane.TabIndex = 11
        '
        'frmHistogram
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 222)
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

    Private Sub frmHistogram_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        cboPlane.SelectedIndex = 0
    End Sub

    Private Sub btnShow_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnShow.Click

        ' Clear the histogram using Array.Clear here

        ApplyEffect()

        picHistogram.Refresh()
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Dim color As Color = image.GetPixel(x, y)


        ' Count the histogram using the selected colour plane here
        ' Note: there are 256 boxes in the histogram


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
End Class
