Option Strict Off

Public Class frmImage
    Inherits System.Windows.Forms.Form
#Region "Windows Form Designer generated code "
    Public Sub New()
        MyBase.New()
     
        'This call is required by the Windows Form Designer.
        InitializeComponent()
    End Sub
    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal Disposing As Boolean)
        If Disposing Then
            If Not components Is Nothing Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(Disposing)
    End Sub
    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer
    Public ToolTip1 As System.Windows.Forms.ToolTip
    Public WithEvents picImage As System.Windows.Forms.PictureBox
    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.picImage = New System.Windows.Forms.PictureBox
        CType(Me.picImage, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'picImage
        '
        Me.picImage.BackColor = System.Drawing.SystemColors.Window
        Me.picImage.Cursor = System.Windows.Forms.Cursors.Default
        Me.picImage.ForeColor = System.Drawing.Color.White
        Me.picImage.Location = New System.Drawing.Point(0, 0)
        Me.picImage.Name = "picImage"
        Me.picImage.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me.picImage.Size = New System.Drawing.Size(262, 128)
        Me.picImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize
        Me.picImage.TabIndex = 0
        Me.picImage.TabStop = False
        '
        'frmImage
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(263, 129)
        Me.Controls.Add(Me.picImage)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.Location = New System.Drawing.Point(3, 22)
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmImage"
        Me.Text = "Image"
        CType(Me.picImage, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
#End Region

    Private selectedPoint As Point? = Nothing
    Public selectedArea As Rectangle

    Public image As Bitmap      ' The image source
    Public filename As String   ' The file name of the image

    Public Function ShowPicture(ByRef fileName As String) As Boolean
        If Not image Is Nothing Then
            image.Dispose()
        End If

        Me.filename = filename

        selectedPoint = Nothing
        selectedArea = New Rectangle(0, 0, 0, 0)

        Me.Show()

        Try
            Dim file As Bitmap = New Bitmap(fileName)

            image = New Bitmap(file.Width, file.Height, Imaging.PixelFormat.Format24bppRgb)

            Dim g As Graphics = Graphics.FromImage(image)
            g.Clear(Color.White)
            g.DrawImage(file, 0, 0, file.Width, file.Height)

            picImage.SizeMode = PictureBoxSizeMode.StretchImage
            picImage.Width = image.Width
            picImage.Height = image.Height
            picImage.Image = CType(image, Image)

            Me.Width = picImage.Width + Me.Width - Me.ClientRectangle.Width
            Me.Height = picImage.Height + Me.Height - Me.ClientRectangle.Height

            Return True
        Catch e As Exception
            MessageBox.Show(e.Message, "Show Picture Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
            Me.Close()

            Return False
        End Try
    End Function

    Public Function ShowPicture(ByVal width As Integer, ByVal height As Integer) As Boolean
        If Not image Is Nothing Then
            image.Dispose()
        End If

        Me.filename = String.Empty

        selectedPoint = Nothing
        selectedArea = New Rectangle(0, 0, 0, 0)

        Me.Show()

        Try
            image = New Bitmap(width, height, Imaging.PixelFormat.Format24bppRgb)

            picImage.SizeMode = PictureBoxSizeMode.StretchImage
            picImage.Width = image.Width
            picImage.Height = image.Height
            picImage.Image = CType(image, Image)

            Me.Width = picImage.Width + Me.Width - Me.ClientRectangle.Width
            Me.Height = picImage.Height + Me.Height - Me.ClientRectangle.Height

            Return True
        Catch e As Exception
            MessageBox.Show(e.Message, "Show Picture Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
            Me.Close()

            Return False
        End Try
    End Function

    Public Sub SetPicture(ByRef image As Bitmap)
        If Not Me.image Is Nothing Then
            Me.image.Dispose()
        End If

        Me.image = image
        picImage.Image = CType(Me.image, Image)
    End Sub

    Public Sub ResetPicture()
        Dim width As Integer = 0
        Dim height As Integer = 0
        If Not image Is Nothing Then
            width = image.Width
            height = image.Height
            image.Dispose()
        End If

        If String.IsNullOrEmpty(filename) Then
            image = New Bitmap(width, height, Imaging.PixelFormat.Format24bppRgb)
        Else
            image = New Bitmap(filename)
        End If
        picImage.Image = CType(image, Image)
    End Sub

    Public Sub SelectNone()
        RemoveDashRect()
        selectedArea = New Rectangle(0, 0, 0, 0)
        DrawDashRect()
    End Sub

    Public Sub SelectAll()
        RemoveDashRect()
        selectedArea = New Rectangle(0, 0, picImage.ClientRectangle.Width - 1, picImage.ClientRectangle.Height - 1)
        DrawDashRect()
    End Sub

    Public Sub DrawDashRect(Optional ByVal g As Graphics = Nothing)
        If g Is Nothing Then g = picImage.CreateGraphics()

        Dim pen As New Pen(Color.LightGray, 1)
        pen.DashStyle = Drawing.Drawing2D.DashStyle.Dash

        g.DrawRectangle(pen, selectedArea)
    End Sub

    Public Sub RemoveDashRect()
        Dim g As Graphics = picImage.CreateGraphics()

        Dim textureBrush As New TextureBrush(picImage.Image)
        Dim pen As New Pen(textureBrush, 1)

        g.DrawRectangle(pen, selectedArea)
    End Sub

    Private Sub picImage_MouseDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picImage.MouseDown
        RemoveDashRect()
        selectedPoint = New Point(e.X, e.Y)
        selectedArea = New Rectangle(selectedPoint, New Size(0, 0))
        DrawDashRect()
    End Sub

    Private Sub picImage_MouseMove(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picImage.MouseMove
        If Not selectedPoint Is Nothing Then
            RemoveDashRect()
            selectedArea.X = IIf(selectedPoint.Value.X < e.X, selectedPoint.Value.X, e.X)
            selectedArea.Width = Math.Abs(e.X - selectedPoint.Value.X)
            selectedArea.Y = IIf(selectedPoint.Value.Y < e.Y, selectedPoint.Value.Y, e.Y)
            selectedArea.Height = Math.Abs(e.Y - selectedPoint.Value.Y)
            DrawDashRect()
        End If
    End Sub

    Private Sub picImage_MouseUp(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picImage.MouseUp
        If Not selectedPoint Is Nothing Then
            RemoveDashRect()
            selectedArea.X = IIf(selectedPoint.Value.X < e.X, selectedPoint.Value.X, e.X)
            selectedArea.Width = Math.Abs(e.X - selectedPoint.Value.X)
            selectedArea.Y = IIf(selectedPoint.Value.Y < e.Y, selectedPoint.Value.Y, e.Y)
            selectedArea.Height = Math.Abs(e.Y - selectedPoint.Value.Y)
            selectedArea.Intersect(New Rectangle(0, 0, picImage.ClientRectangle.Width - 1, picImage.ClientRectangle.Height - 1))
            DrawDashRect()
            selectedPoint = Nothing
        End If
    End Sub


    Private Sub picImage_Paint(ByVal sender As Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles picImage.Paint
        DrawDashRect(e.Graphics())
    End Sub

    Private Sub frmImage_Enter(ByVal sender As Object, ByVal e As System.EventArgs) Handles MyBase.Enter
        DirectCast(Me.MdiParent, frmMain).selectedImageForm = Me
    End Sub

    Private Sub frmImage_Closed(ByVal sender As Object, ByVal e As System.EventArgs) Handles MyBase.Closed
        DirectCast(Me.MdiParent, frmMain).selectedImageForm = Nothing
    End Sub
End Class