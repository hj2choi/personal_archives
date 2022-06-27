Public Class frmMelt
    Inherits frmSpatialEffect

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
    Friend WithEvents gbxSine As System.Windows.Forms.GroupBox
    Friend WithEvents tbAmplitude As System.Windows.Forms.TrackBar
    Friend WithEvents lblAmplitude As System.Windows.Forms.Label
    Friend WithEvents lblCycle As System.Windows.Forms.Label
    Friend WithEvents tbCycle As System.Windows.Forms.TrackBar
    Friend WithEvents gbxRandom As System.Windows.Forms.GroupBox
    Friend WithEvents lblPeriod As System.Windows.Forms.Label
    Friend WithEvents tbPeriod As System.Windows.Forms.TrackBar
    Friend WithEvents lblOffset As System.Windows.Forms.Label
    Friend WithEvents cbxSine As System.Windows.Forms.CheckBox
    Friend WithEvents cbxRandom As System.Windows.Forms.CheckBox
    Friend WithEvents tbarAngle As System.Windows.Forms.TrackBar
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents tbOffset As System.Windows.Forms.TrackBar
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnApply = New System.Windows.Forms.Button()
        Me.gbxSine = New System.Windows.Forms.GroupBox()
        Me.cbxSine = New System.Windows.Forms.CheckBox()
        Me.lblCycle = New System.Windows.Forms.Label()
        Me.tbCycle = New System.Windows.Forms.TrackBar()
        Me.lblAmplitude = New System.Windows.Forms.Label()
        Me.tbAmplitude = New System.Windows.Forms.TrackBar()
        Me.gbxRandom = New System.Windows.Forms.GroupBox()
        Me.cbxRandom = New System.Windows.Forms.CheckBox()
        Me.lblPeriod = New System.Windows.Forms.Label()
        Me.tbPeriod = New System.Windows.Forms.TrackBar()
        Me.lblOffset = New System.Windows.Forms.Label()
        Me.tbOffset = New System.Windows.Forms.TrackBar()
        Me.tbarAngle = New System.Windows.Forms.TrackBar()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.gbxSine.SuspendLayout()
        CType(Me.tbCycle, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbAmplitude, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbxRandom.SuspendLayout()
        CType(Me.tbPeriod, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbOffset, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbarAngle, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(128, 340)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(96, 28)
        Me.btnApply.TabIndex = 8
        Me.btnApply.Text = "Apply"
        '
        'gbxSine
        '
        Me.gbxSine.Controls.Add(Me.cbxSine)
        Me.gbxSine.Controls.Add(Me.lblCycle)
        Me.gbxSine.Controls.Add(Me.tbCycle)
        Me.gbxSine.Controls.Add(Me.lblAmplitude)
        Me.gbxSine.Controls.Add(Me.tbAmplitude)
        Me.gbxSine.Location = New System.Drawing.Point(14, 14)
        Me.gbxSine.Name = "gbxSine"
        Me.gbxSine.Size = New System.Drawing.Size(324, 121)
        Me.gbxSine.TabIndex = 10
        Me.gbxSine.TabStop = False
        '
        'cbxSine
        '
        Me.cbxSine.AutoSize = True
        Me.cbxSine.Location = New System.Drawing.Point(7, -1)
        Me.cbxSine.Name = "cbxSine"
        Me.cbxSine.Size = New System.Drawing.Size(147, 21)
        Me.cbxSine.TabIndex = 12
        Me.cbxSine.Text = "Sine Displacement"
        Me.cbxSine.UseVisualStyleBackColor = True
        '
        'lblCycle
        '
        Me.lblCycle.AutoSize = True
        Me.lblCycle.Location = New System.Drawing.Point(14, 74)
        Me.lblCycle.Name = "lblCycle"
        Me.lblCycle.Size = New System.Drawing.Size(46, 17)
        Me.lblCycle.TabIndex = 6
        Me.lblCycle.Text = "Cycle:"
        '
        'tbCycle
        '
        Me.tbCycle.Location = New System.Drawing.Point(89, 70)
        Me.tbCycle.Minimum = 1
        Me.tbCycle.Name = "tbCycle"
        Me.tbCycle.Size = New System.Drawing.Size(221, 56)
        Me.tbCycle.TabIndex = 5
        Me.tbCycle.Value = 3
        '
        'lblAmplitude
        '
        Me.lblAmplitude.AutoSize = True
        Me.lblAmplitude.Location = New System.Drawing.Point(14, 25)
        Me.lblAmplitude.Name = "lblAmplitude"
        Me.lblAmplitude.Size = New System.Drawing.Size(74, 17)
        Me.lblAmplitude.TabIndex = 4
        Me.lblAmplitude.Text = "Amplitude:"
        '
        'tbAmplitude
        '
        Me.tbAmplitude.Location = New System.Drawing.Point(89, 22)
        Me.tbAmplitude.Minimum = 1
        Me.tbAmplitude.Name = "tbAmplitude"
        Me.tbAmplitude.Size = New System.Drawing.Size(221, 56)
        Me.tbAmplitude.TabIndex = 0
        Me.tbAmplitude.Value = 7
        '
        'gbxRandom
        '
        Me.gbxRandom.Controls.Add(Me.cbxRandom)
        Me.gbxRandom.Controls.Add(Me.lblPeriod)
        Me.gbxRandom.Controls.Add(Me.tbPeriod)
        Me.gbxRandom.Controls.Add(Me.lblOffset)
        Me.gbxRandom.Controls.Add(Me.tbOffset)
        Me.gbxRandom.Location = New System.Drawing.Point(14, 147)
        Me.gbxRandom.Name = "gbxRandom"
        Me.gbxRandom.Size = New System.Drawing.Size(324, 121)
        Me.gbxRandom.TabIndex = 11
        Me.gbxRandom.TabStop = False
        '
        'cbxRandom
        '
        Me.cbxRandom.AutoSize = True
        Me.cbxRandom.Location = New System.Drawing.Point(7, -1)
        Me.cbxRandom.Name = "cbxRandom"
        Me.cbxRandom.Size = New System.Drawing.Size(172, 21)
        Me.cbxRandom.TabIndex = 13
        Me.cbxRandom.Text = "Random Displacement"
        Me.cbxRandom.UseVisualStyleBackColor = True
        '
        'lblPeriod
        '
        Me.lblPeriod.AutoSize = True
        Me.lblPeriod.Location = New System.Drawing.Point(14, 74)
        Me.lblPeriod.Name = "lblPeriod"
        Me.lblPeriod.Size = New System.Drawing.Size(53, 17)
        Me.lblPeriod.TabIndex = 6
        Me.lblPeriod.Text = "Period:"
        '
        'tbPeriod
        '
        Me.tbPeriod.Location = New System.Drawing.Point(89, 70)
        Me.tbPeriod.Minimum = 1
        Me.tbPeriod.Name = "tbPeriod"
        Me.tbPeriod.Size = New System.Drawing.Size(221, 56)
        Me.tbPeriod.TabIndex = 5
        Me.tbPeriod.Value = 3
        '
        'lblOffset
        '
        Me.lblOffset.AutoSize = True
        Me.lblOffset.Location = New System.Drawing.Point(14, 25)
        Me.lblOffset.Name = "lblOffset"
        Me.lblOffset.Size = New System.Drawing.Size(50, 17)
        Me.lblOffset.TabIndex = 4
        Me.lblOffset.Text = "Offset:"
        '
        'tbOffset
        '
        Me.tbOffset.Location = New System.Drawing.Point(89, 22)
        Me.tbOffset.Minimum = 1
        Me.tbOffset.Name = "tbOffset"
        Me.tbOffset.Size = New System.Drawing.Size(221, 56)
        Me.tbOffset.TabIndex = 0
        Me.tbOffset.Value = 3
        '
        'tbarAngle
        '
        Me.tbarAngle.LargeChange = 100
        Me.tbarAngle.Location = New System.Drawing.Point(103, 279)
        Me.tbarAngle.Maximum = 359
        Me.tbarAngle.Name = "tbarAngle"
        Me.tbarAngle.Size = New System.Drawing.Size(221, 56)
        Me.tbarAngle.SmallChange = 30
        Me.tbarAngle.TabIndex = 12
        Me.tbarAngle.TickFrequency = 30
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(31, 288)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(43, 17)
        Me.Label1.TabIndex = 13
        Me.Label1.Text = "angle"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(109, 318)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(16, 17)
        Me.Label2.TabIndex = 14
        Me.Label2.Text = "0"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(296, 318)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(32, 17)
        Me.Label3.TabIndex = 14
        Me.Label3.Text = "360"
        '
        'frmMelt
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(379, 380)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.tbarAngle)
        Me.Controls.Add(Me.gbxRandom)
        Me.Controls.Add(Me.gbxSine)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmMelt"
        Me.Text = "Melt"
        Me.gbxSine.ResumeLayout(False)
        Me.gbxSine.PerformLayout()
        CType(Me.tbCycle, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbAmplitude, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbxRandom.ResumeLayout(False)
        Me.gbxRandom.PerformLayout()
        CType(Me.tbPeriod, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbOffset, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbarAngle, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

#End Region

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Sub Process(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle)
        Melt(input, output, rect, cbxSine.Checked, tbAmplitude.Value, tbCycle.Value, cbxRandom.Checked, tbOffset.Value, tbPeriod.Value, tbarAngle.Value)
    End Sub
End Class
