Public Class frmKernel
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
    Friend WithEvents btnApply As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnApply = New System.Windows.Forms.Button
        Me.SuspendLayout()
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 12)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
        Me.btnApply.TabIndex = 8
        Me.btnApply.Text = "Apply"
        '
        'frmKernel
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 48)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmKernel"
        Me.Text = "Kernel"
        Me.ResumeLayout(False)

    End Sub

#End Region

    Public kernel(2, 2) As Double            ' The kernel (default: 3x3 matrix. Will be changed when you load your kernel.)
    Public kernel_width As Integer           ' The kernel width
    Public kernel_height As Integer          ' The kernel height

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click

        ' Load image file and convert it into kernel before applying the effect
        Dim dlg As New OpenFileDialog

        dlg.Filter = "Image Files (.bmp,.jpg,.png)|*.bmp;*.jpg;*.png"
        dlg.FilterIndex = 1
        dlg.RestoreDirectory = True

        If dlg.ShowDialog() = DialogResult.OK Then
            If Len(dlg.FileName) = 0 Then Exit Sub

            Dim image As Bitmap = New Bitmap(dlg.FileName)

            kernel_width = image.Width
            kernel_height = image.Height
            ReDim kernel(kernel_width - 1, kernel_height - 1)

            Dim totalSum As Double

            'load image brightness to kernel
            totalSum = 0
            For y As Integer = 0 To (image.Height - 1)
                For x As Integer = 0 To (image.Width - 1)
                    kernel(x, y) = image.GetPixel(x, y).GetBrightness()
                    totalSum += kernel(x, y)
                Next
            Next

            'normalize the kernel
            If (totalSum > 0.0001) Then
                For y As Integer = 0 To (image.Height - 1)
                    For x As Integer = 0 To (image.Width - 1)
                        kernel(x, y) = kernel(x, y) / totalSum
                    Next
                Next
            End If

            ApplyEffect()

        End If
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color

        'Convolute your image by using the 'kernel' defined in this form
        'The size of the kernel is kernel_width x kernel_height
        'Hint: Use nested for-loop (2 level) to loop through the kernel

        'For your convenience, the excrept of the declaration is here:
		'================================================================
	    'Public kernel(2, 2) As Double            ' The kernel (default: 3x3 matrix. Will be changed when you load your kernel.)
		'Public kernel_width As Integer           ' The kernel width
		'Public kernel_height As Integer          ' The kernel height
		'================================================================

		'Q) How to define filter center when you encounter even number for the kernel width/height?
		'A) Use the following equations to calculate the filter center:
		'Dim cx As Integer = Math.Floor(kernel_width / 2.0)
        'Dim cy As Integer = Math.Floor(kernel_height / 2.0)

        Dim pixColor As Color

		'Your code here

        Return pixColor

    End Function
End Class
