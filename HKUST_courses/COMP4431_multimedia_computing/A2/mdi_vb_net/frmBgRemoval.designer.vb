<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class frmBgRemoval
    Inherits frmEffect

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing AndAlso components IsNot Nothing Then
            components.Dispose()
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.tbarThreshold = New System.Windows.Forms.TrackBar()
        Me.rbtn8Dir = New System.Windows.Forms.RadioButton()
        Me.rbtn4Dir = New System.Windows.Forms.RadioButton()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.tboxY = New System.Windows.Forms.TextBox()
        Me.tboxX = New System.Windows.Forms.TextBox()
        Me.btnApply = New System.Windows.Forms.Button()
        CType(Me.tbarThreshold, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(268, 122)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(32, 17)
        Me.Label5.TabIndex = 9
        Me.Label5.Text = "100"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(78, 122)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(16, 17)
        Me.Label4.TabIndex = 9
        Me.Label4.Text = "1"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(2, 93)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(72, 17)
        Me.Label3.TabIndex = 8
        Me.Label3.Text = "Threshold"
        '
        'tbarThreshold
        '
        Me.tbarThreshold.LargeChange = 10
        Me.tbarThreshold.Location = New System.Drawing.Point(70, 83)
        Me.tbarThreshold.Maximum = 100
        Me.tbarThreshold.Minimum = 1
        Me.tbarThreshold.Name = "tbarThreshold"
        Me.tbarThreshold.Size = New System.Drawing.Size(224, 56)
        Me.tbarThreshold.SmallChange = 5
        Me.tbarThreshold.TabIndex = 7
        Me.tbarThreshold.TickFrequency = 20
        Me.tbarThreshold.Value = 1
        '
        'rbtn8Dir
        '
        Me.rbtn8Dir.AutoSize = True
        Me.rbtn8Dir.Location = New System.Drawing.Point(30, 39)
        Me.rbtn8Dir.Name = "rbtn8Dir"
        Me.rbtn8Dir.Size = New System.Drawing.Size(154, 21)
        Me.rbtn8Dir.TabIndex = 6
        Me.rbtn8Dir.Text = "8 directions flood fill"
        Me.rbtn8Dir.UseVisualStyleBackColor = True
        '
        'rbtn4Dir
        '
        Me.rbtn4Dir.AutoSize = True
        Me.rbtn4Dir.Checked = True
        Me.rbtn4Dir.Location = New System.Drawing.Point(30, 12)
        Me.rbtn4Dir.Name = "rbtn4Dir"
        Me.rbtn4Dir.Size = New System.Drawing.Size(154, 21)
        Me.rbtn4Dir.TabIndex = 5
        Me.rbtn4Dir.TabStop = True
        Me.rbtn4Dir.Text = "4 directions flood fill"
        Me.rbtn4Dir.UseVisualStyleBackColor = True
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(26, 186)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(78, 17)
        Me.Label2.TabIndex = 4
        Me.Label2.Text = "initial y pos"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(27, 159)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(77, 17)
        Me.Label1.TabIndex = 3
        Me.Label1.Text = "initial x pos"
        '
        'tboxY
        '
        Me.tboxY.Location = New System.Drawing.Point(120, 181)
        Me.tboxY.Name = "tboxY"
        Me.tboxY.Size = New System.Drawing.Size(100, 22)
        Me.tboxY.TabIndex = 2
        '
        'tboxX
        '
        Me.tboxX.Location = New System.Drawing.Point(119, 156)
        Me.tboxX.Name = "tboxX"
        Me.tboxX.Size = New System.Drawing.Size(101, 22)
        Me.tboxX.TabIndex = 1
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(81, 221)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(122, 40)
        Me.btnApply.TabIndex = 0
        Me.btnApply.Text = "Apply"
        Me.btnApply.UseVisualStyleBackColor = True
        '
        'frmBgRemoval
        '
        Me.ClientSize = New System.Drawing.Size(306, 273)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.tbarThreshold)
        Me.Controls.Add(Me.rbtn8Dir)
        Me.Controls.Add(Me.rbtn4Dir)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.tboxY)
        Me.Controls.Add(Me.tboxX)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmBgRemoval"
        Me.Text = "Background removal"
        CType(Me.tbarThreshold, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents tboxX As System.Windows.Forms.TextBox
    Friend WithEvents tboxY As System.Windows.Forms.TextBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents rbtn4Dir As System.Windows.Forms.RadioButton
    Friend WithEvents rbtn8Dir As System.Windows.Forms.RadioButton
    Friend WithEvents tbarThreshold As System.Windows.Forms.TrackBar
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label

End Class
