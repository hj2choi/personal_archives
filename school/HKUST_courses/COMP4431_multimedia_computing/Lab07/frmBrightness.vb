Public Class frmBrightness
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
    Friend WithEvents tbOffset As System.Windows.Forms.TrackBar
    Public WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents gbxBrightness As System.Windows.Forms.GroupBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.tbOffset = New System.Windows.Forms.TrackBar
        Me.btnApply = New System.Windows.Forms.Button
        Me.Label1 = New System.Windows.Forms.Label
        Me.Label2 = New System.Windows.Forms.Label
        Me.gbxBrightness = New System.Windows.Forms.GroupBox
        CType(Me.tbOffset, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbxBrightness.SuspendLayout()
        Me.SuspendLayout()
        '
        'tbOffset
        '
        Me.tbOffset.LargeChange = 16
        Me.tbOffset.Location = New System.Drawing.Point(12, 19)
        Me.tbOffset.Maximum = 255
        Me.tbOffset.Minimum = -255
        Me.tbOffset.Name = "tbOffset"
        Me.tbOffset.Size = New System.Drawing.Size(246, 42)
        Me.tbOffset.TabIndex = 15
        Me.tbOffset.TickFrequency = 255
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 102)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
        Me.btnApply.TabIndex = 16
        Me.btnApply.Text = "Apply"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(227, 50)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(31, 13)
        Me.Label1.TabIndex = 17
        Me.Label1.Text = "+255"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(9, 50)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(28, 13)
        Me.Label2.TabIndex = 18
        Me.Label2.Text = "-255"
        '
        'gbxBrightness
        '
        Me.gbxBrightness.Controls.Add(Me.Label2)
        Me.gbxBrightness.Controls.Add(Me.Label1)
        Me.gbxBrightness.Controls.Add(Me.tbOffset)
        Me.gbxBrightness.Location = New System.Drawing.Point(12, 12)
        Me.gbxBrightness.Name = "gbxBrightness"
        Me.gbxBrightness.Size = New System.Drawing.Size(270, 78)
        Me.gbxBrightness.TabIndex = 20
        Me.gbxBrightness.TabStop = False
        Me.gbxBrightness.Text = "Brightness Offset"
        '
        'frmBrightness
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 138)
        Me.Controls.Add(Me.gbxBrightness)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmBrightness"
        Me.Text = "Brightness"
        CType(Me.tbOffset, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbxBrightness.ResumeLayout(False)
        Me.gbxBrightness.PerformLayout()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Return Brightness(image.GetPixel(x, y), tbOffset.Value)
    End Function
End Class
