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
    Friend WithEvents tbOffset As System.Windows.Forms.TrackBar
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnApply = New System.Windows.Forms.Button
        Me.gbxSine = New System.Windows.Forms.GroupBox
        Me.cbxSine = New System.Windows.Forms.CheckBox
        Me.lblCycle = New System.Windows.Forms.Label
        Me.tbCycle = New System.Windows.Forms.TrackBar
        Me.lblAmplitude = New System.Windows.Forms.Label
        Me.tbAmplitude = New System.Windows.Forms.TrackBar
        Me.gbxRandom = New System.Windows.Forms.GroupBox
        Me.cbxRandom = New System.Windows.Forms.CheckBox
        Me.lblPeriod = New System.Windows.Forms.Label
        Me.tbPeriod = New System.Windows.Forms.TrackBar
        Me.lblOffset = New System.Windows.Forms.Label
        Me.tbOffset = New System.Windows.Forms.TrackBar
        Me.gbxSine.SuspendLayout()
        CType(Me.tbCycle, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbAmplitude, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbxRandom.SuspendLayout()
        CType(Me.tbPeriod, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbOffset, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 242)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
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
        Me.gbxSine.Location = New System.Drawing.Point(12, 12)
        Me.gbxSine.Name = "gbxSine"
        Me.gbxSine.Size = New System.Drawing.Size(270, 105)
        Me.gbxSine.TabIndex = 10
        Me.gbxSine.TabStop = False
        '
        'cbxSine
        '
        Me.cbxSine.AutoSize = True
        Me.cbxSine.Location = New System.Drawing.Point(6, -1)
        Me.cbxSine.Name = "cbxSine"
        Me.cbxSine.Size = New System.Drawing.Size(114, 17)
        Me.cbxSine.TabIndex = 12
        Me.cbxSine.Text = "Sine Displacement"
        Me.cbxSine.UseVisualStyleBackColor = True
        '
        'lblCycle
        '
        Me.lblCycle.AutoSize = True
        Me.lblCycle.Location = New System.Drawing.Point(12, 64)
        Me.lblCycle.Name = "lblCycle"
        Me.lblCycle.Size = New System.Drawing.Size(36, 13)
        Me.lblCycle.TabIndex = 6
        Me.lblCycle.Text = "Cycle:"
        '
        'tbCycle
        '
        Me.tbCycle.Location = New System.Drawing.Point(74, 61)
        Me.tbCycle.Minimum = 1
        Me.tbCycle.Name = "tbCycle"
        Me.tbCycle.Size = New System.Drawing.Size(184, 42)
        Me.tbCycle.TabIndex = 5
        Me.tbCycle.Value = 3
        '
        'lblAmplitude
        '
        Me.lblAmplitude.AutoSize = True
        Me.lblAmplitude.Location = New System.Drawing.Point(12, 22)
        Me.lblAmplitude.Name = "lblAmplitude"
        Me.lblAmplitude.Size = New System.Drawing.Size(56, 13)
        Me.lblAmplitude.TabIndex = 4
        Me.lblAmplitude.Text = "Amplitude:"
        '
        'tbAmplitude
        '
        Me.tbAmplitude.Location = New System.Drawing.Point(74, 19)
        Me.tbAmplitude.Minimum = 1
        Me.tbAmplitude.Name = "tbAmplitude"
        Me.tbAmplitude.Size = New System.Drawing.Size(184, 42)
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
        Me.gbxRandom.Location = New System.Drawing.Point(12, 127)
        Me.gbxRandom.Name = "gbxRandom"
        Me.gbxRandom.Size = New System.Drawing.Size(270, 105)
        Me.gbxRandom.TabIndex = 11
        Me.gbxRandom.TabStop = False
        '
        'cbxRandom
        '
        Me.cbxRandom.AutoSize = True
        Me.cbxRandom.Location = New System.Drawing.Point(6, -1)
        Me.cbxRandom.Name = "cbxRandom"
        Me.cbxRandom.Size = New System.Drawing.Size(133, 17)
        Me.cbxRandom.TabIndex = 13
        Me.cbxRandom.Text = "Random Displacement"
        Me.cbxRandom.UseVisualStyleBackColor = True
        '
        'lblPeriod
        '
        Me.lblPeriod.AutoSize = True
        Me.lblPeriod.Location = New System.Drawing.Point(12, 64)
        Me.lblPeriod.Name = "lblPeriod"
        Me.lblPeriod.Size = New System.Drawing.Size(40, 13)
        Me.lblPeriod.TabIndex = 6
        Me.lblPeriod.Text = "Period:"
        '
        'tbPeriod
        '
        Me.tbPeriod.Location = New System.Drawing.Point(74, 61)
        Me.tbPeriod.Minimum = 1
        Me.tbPeriod.Name = "tbPeriod"
        Me.tbPeriod.Size = New System.Drawing.Size(184, 42)
        Me.tbPeriod.TabIndex = 5
        Me.tbPeriod.Value = 3
        '
        'lblOffset
        '
        Me.lblOffset.AutoSize = True
        Me.lblOffset.Location = New System.Drawing.Point(12, 22)
        Me.lblOffset.Name = "lblOffset"
        Me.lblOffset.Size = New System.Drawing.Size(38, 13)
        Me.lblOffset.TabIndex = 4
        Me.lblOffset.Text = "Offset:"
        '
        'tbOffset
        '
        Me.tbOffset.Location = New System.Drawing.Point(74, 19)
        Me.tbOffset.Minimum = 1
        Me.tbOffset.Name = "tbOffset"
        Me.tbOffset.Size = New System.Drawing.Size(184, 42)
        Me.tbOffset.TabIndex = 0
        Me.tbOffset.Value = 3
        '
        'frmMelt
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 278)
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
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Sub Process(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle)
        Melt(input, output, rect, cbxSine.Checked, tbAmplitude.Value, tbCycle.Value, cbxRandom.Checked, tbOffset.Value, tbPeriod.Value)
    End Sub
End Class
