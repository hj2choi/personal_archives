Public Class frmColorFill
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
    Friend WithEvents btnColor As System.Windows.Forms.Button
    Friend WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents gbxColor As System.Windows.Forms.GroupBox
    Friend WithEvents btnChoose As System.Windows.Forms.Button
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnColor = New System.Windows.Forms.Button
        Me.btnApply = New System.Windows.Forms.Button
        Me.gbxColor = New System.Windows.Forms.GroupBox
        Me.btnChoose = New System.Windows.Forms.Button
        Me.gbxColor.SuspendLayout()
        Me.SuspendLayout()
        '
        'btnColor
        '
        Me.btnColor.BackColor = System.Drawing.Color.Black
        Me.btnColor.ForeColor = System.Drawing.SystemColors.WindowText
        Me.btnColor.Location = New System.Drawing.Point(68, 19)
        Me.btnColor.Name = "btnColor"
        Me.btnColor.Size = New System.Drawing.Size(48, 48)
        Me.btnColor.TabIndex = 1
        Me.btnColor.UseVisualStyleBackColor = False
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 104)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
        Me.btnApply.TabIndex = 2
        Me.btnApply.Text = "Apply"
        '
        'gbxColor
        '
        Me.gbxColor.Controls.Add(Me.btnChoose)
        Me.gbxColor.Controls.Add(Me.btnColor)
        Me.gbxColor.Location = New System.Drawing.Point(12, 12)
        Me.gbxColor.Name = "gbxColor"
        Me.gbxColor.Size = New System.Drawing.Size(270, 80)
        Me.gbxColor.TabIndex = 3
        Me.gbxColor.TabStop = False
        Me.gbxColor.Text = "Please choose a colour:"
        '
        'btnChoose
        '
        Me.btnChoose.Location = New System.Drawing.Point(128, 31)
        Me.btnChoose.Name = "btnChoose"
        Me.btnChoose.Size = New System.Drawing.Size(75, 24)
        Me.btnChoose.TabIndex = 3
        Me.btnChoose.Text = "Choose"
        '
        'frmColorFill
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 140)
        Me.Controls.Add(Me.gbxColor)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.Name = "frmColorFill"
        Me.Text = "Color Fill"
        Me.gbxColor.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnColor_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnColor.Click, btnChoose.Click
        Dim dlg As New ColorDialog
        dlg.ShowDialog()
        Me.btnColor.BackColor = dlg.Color
    End Sub

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color

        ' Apply the selected colour here

        Return Me.btnColor.BackColor
    End Function
End Class
