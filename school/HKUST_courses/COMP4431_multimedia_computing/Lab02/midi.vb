Option Explicit On 
Public Class Form1
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
    Private WithEvents vol As System.Windows.Forms.VScrollBar
    Public WithEvents _key_15 As System.Windows.Forms.CheckBox
    Public WithEvents _key_13 As System.Windows.Forms.CheckBox
    Public WithEvents _key_10 As System.Windows.Forms.CheckBox
    Public WithEvents _key_8 As System.Windows.Forms.CheckBox
    Public WithEvents _key_6 As System.Windows.Forms.CheckBox
    Public WithEvents _key_3 As System.Windows.Forms.CheckBox
    Public WithEvents _key_1 As System.Windows.Forms.CheckBox
    Public WithEvents _key_16 As System.Windows.Forms.CheckBox
    Public WithEvents _key_14 As System.Windows.Forms.CheckBox
    Public WithEvents _key_12 As System.Windows.Forms.CheckBox
    Public WithEvents _key_11 As System.Windows.Forms.CheckBox
    Public WithEvents _key_9 As System.Windows.Forms.CheckBox
    Public WithEvents _key_7 As System.Windows.Forms.CheckBox
    Public WithEvents _key_5 As System.Windows.Forms.CheckBox
    Public WithEvents _key_4 As System.Windows.Forms.CheckBox
    Public WithEvents _key_2 As System.Windows.Forms.CheckBox
    Public WithEvents _key_0 As System.Windows.Forms.CheckBox
    Private WithEvents Label1 As System.Windows.Forms.Label
    Public WithEvents _device_0 As System.Windows.Forms.MenuItem
    Public WithEvents _device_1 As System.Windows.Forms.MenuItem
    Public WithEvents _device_2 As System.Windows.Forms.MenuItem
    Public WithEvents _device_3 As System.Windows.Forms.MenuItem
    Public WithEvents _device_4 As System.Windows.Forms.MenuItem
    Public WithEvents _device_5 As System.Windows.Forms.MenuItem
    Public WithEvents _device_6 As System.Windows.Forms.MenuItem
    Public WithEvents _device_7 As System.Windows.Forms.MenuItem
    Public WithEvents _device_8 As System.Windows.Forms.MenuItem
    Public WithEvents _device_9 As System.Windows.Forms.MenuItem
    Public WithEvents _device_10 As System.Windows.Forms.MenuItem
    Public WithEvents midi_devices As System.Windows.Forms.MenuItem
    Public WithEvents _chan_0 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_1 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_2 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_3 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_4 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_5 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_6 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_7 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_8 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_9 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_10 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_11 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_12 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_13 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_14 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_15 As System.Windows.Forms.MenuItem
    Public WithEvents ChannelOption As System.Windows.Forms.MenuItem
    Public WithEvents base As System.Windows.Forms.MenuItem
    Public MainMenu1 As System.Windows.Forms.MainMenu
    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.
    'Do not modify it using the code editor.
    Public WithEvents Frame1 As System.Windows.Forms.GroupBox
    Private WithEvents btnPlay As System.Windows.Forms.Button
    Private WithEvents btnStop As System.Windows.Forms.Button
    Private WithEvents btnRecord As System.Windows.Forms.Button
    Private WithEvents btnRemoveSilence As System.Windows.Forms.Button
    Friend WithEvents tbSpeed As System.Windows.Forms.TrackBar
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents tbBankMSB As System.Windows.Forms.TrackBar
    Friend WithEvents tbInstrument As System.Windows.Forms.TrackBar
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents tmrSequencer As System.Windows.Forms.Timer
    Friend WithEvents txtStatus As System.Windows.Forms.Label
    Friend WithEvents txtMidiSignal As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.vol = New System.Windows.Forms.VScrollBar()
        Me._key_15 = New System.Windows.Forms.CheckBox()
        Me._key_13 = New System.Windows.Forms.CheckBox()
        Me._key_10 = New System.Windows.Forms.CheckBox()
        Me._key_8 = New System.Windows.Forms.CheckBox()
        Me._key_6 = New System.Windows.Forms.CheckBox()
        Me._key_3 = New System.Windows.Forms.CheckBox()
        Me._key_1 = New System.Windows.Forms.CheckBox()
        Me._key_16 = New System.Windows.Forms.CheckBox()
        Me._key_14 = New System.Windows.Forms.CheckBox()
        Me._key_12 = New System.Windows.Forms.CheckBox()
        Me._key_11 = New System.Windows.Forms.CheckBox()
        Me._key_9 = New System.Windows.Forms.CheckBox()
        Me._key_7 = New System.Windows.Forms.CheckBox()
        Me._key_5 = New System.Windows.Forms.CheckBox()
        Me._key_4 = New System.Windows.Forms.CheckBox()
        Me._key_2 = New System.Windows.Forms.CheckBox()
        Me._key_0 = New System.Windows.Forms.CheckBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me._chan_0 = New System.Windows.Forms.MenuItem()
        Me._chan_1 = New System.Windows.Forms.MenuItem()
        Me._chan_2 = New System.Windows.Forms.MenuItem()
        Me._chan_3 = New System.Windows.Forms.MenuItem()
        Me._chan_4 = New System.Windows.Forms.MenuItem()
        Me._chan_5 = New System.Windows.Forms.MenuItem()
        Me._chan_6 = New System.Windows.Forms.MenuItem()
        Me._chan_7 = New System.Windows.Forms.MenuItem()
        Me._chan_8 = New System.Windows.Forms.MenuItem()
        Me._chan_9 = New System.Windows.Forms.MenuItem()
        Me._chan_10 = New System.Windows.Forms.MenuItem()
        Me._chan_11 = New System.Windows.Forms.MenuItem()
        Me._chan_12 = New System.Windows.Forms.MenuItem()
        Me._chan_13 = New System.Windows.Forms.MenuItem()
        Me._chan_14 = New System.Windows.Forms.MenuItem()
        Me._chan_15 = New System.Windows.Forms.MenuItem()
        Me._device_0 = New System.Windows.Forms.MenuItem()
        Me._device_1 = New System.Windows.Forms.MenuItem()
        Me._device_2 = New System.Windows.Forms.MenuItem()
        Me._device_3 = New System.Windows.Forms.MenuItem()
        Me._device_4 = New System.Windows.Forms.MenuItem()
        Me._device_5 = New System.Windows.Forms.MenuItem()
        Me._device_6 = New System.Windows.Forms.MenuItem()
        Me._device_7 = New System.Windows.Forms.MenuItem()
        Me._device_8 = New System.Windows.Forms.MenuItem()
        Me._device_9 = New System.Windows.Forms.MenuItem()
        Me._device_10 = New System.Windows.Forms.MenuItem()
        Me.MainMenu1 = New System.Windows.Forms.MainMenu(Me.components)
        Me.midi_devices = New System.Windows.Forms.MenuItem()
        Me.ChannelOption = New System.Windows.Forms.MenuItem()
        Me.base = New System.Windows.Forms.MenuItem()
        Me.Frame1 = New System.Windows.Forms.GroupBox()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.tbSpeed = New System.Windows.Forms.TrackBar()
        Me.btnRemoveSilence = New System.Windows.Forms.Button()
        Me.btnPlay = New System.Windows.Forms.Button()
        Me.btnStop = New System.Windows.Forms.Button()
        Me.btnRecord = New System.Windows.Forms.Button()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.tbBankMSB = New System.Windows.Forms.TrackBar()
        Me.tbInstrument = New System.Windows.Forms.TrackBar()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.tmrSequencer = New System.Windows.Forms.Timer(Me.components)
        Me.txtStatus = New System.Windows.Forms.Label()
        Me.txtMidiSignal = New System.Windows.Forms.Label()
        Me.Frame1.SuspendLayout()
        CType(Me.tbSpeed, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.GroupBox1.SuspendLayout()
        CType(Me.tbBankMSB, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbInstrument, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'vol
        '
        Me.vol.LargeChange = 1
        Me.vol.Location = New System.Drawing.Point(0, 27)
        Me.vol.Maximum = 32767
        Me.vol.Name = "vol"
        Me.vol.Size = New System.Drawing.Size(20, 131)
        Me.vol.TabIndex = 17
        Me.vol.TabStop = True
        '
        '_key_15
        '
        Me._key_15.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_15.BackColor = System.Drawing.Color.Black
        Me._key_15.ForeColor = System.Drawing.Color.White
        Me._key_15.Location = New System.Drawing.Point(365, 0)
        Me._key_15.Name = "_key_15"
        Me._key_15.Size = New System.Drawing.Size(20, 97)
        Me._key_15.TabIndex = 16
        Me._key_15.Text = ";"
        Me._key_15.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_15.UseVisualStyleBackColor = False
        '
        '_key_13
        '
        Me._key_13.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_13.BackColor = System.Drawing.Color.Black
        Me._key_13.ForeColor = System.Drawing.Color.White
        Me._key_13.Location = New System.Drawing.Point(326, 0)
        Me._key_13.Name = "_key_13"
        Me._key_13.Size = New System.Drawing.Size(21, 97)
        Me._key_13.TabIndex = 15
        Me._key_13.Text = "L"
        Me._key_13.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_13.UseVisualStyleBackColor = False
        '
        '_key_10
        '
        Me._key_10.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_10.BackColor = System.Drawing.Color.Black
        Me._key_10.ForeColor = System.Drawing.Color.White
        Me._key_10.Location = New System.Drawing.Point(250, 0)
        Me._key_10.Name = "_key_10"
        Me._key_10.Size = New System.Drawing.Size(20, 97)
        Me._key_10.TabIndex = 14
        Me._key_10.Text = "J"
        Me._key_10.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_10.UseVisualStyleBackColor = False
        '
        '_key_8
        '
        Me._key_8.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_8.BackColor = System.Drawing.Color.Black
        Me._key_8.ForeColor = System.Drawing.Color.White
        Me._key_8.Location = New System.Drawing.Point(211, 0)
        Me._key_8.Name = "_key_8"
        Me._key_8.Size = New System.Drawing.Size(21, 97)
        Me._key_8.TabIndex = 13
        Me._key_8.Text = "H"
        Me._key_8.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_8.UseVisualStyleBackColor = False
        '
        '_key_6
        '
        Me._key_6.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_6.BackColor = System.Drawing.Color.Black
        Me._key_6.ForeColor = System.Drawing.Color.White
        Me._key_6.Location = New System.Drawing.Point(173, 0)
        Me._key_6.Name = "_key_6"
        Me._key_6.Size = New System.Drawing.Size(20, 97)
        Me._key_6.TabIndex = 12
        Me._key_6.Text = "G"
        Me._key_6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_6.UseVisualStyleBackColor = False
        '
        '_key_3
        '
        Me._key_3.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_3.BackColor = System.Drawing.Color.Black
        Me._key_3.ForeColor = System.Drawing.Color.White
        Me._key_3.Location = New System.Drawing.Point(96, 0)
        Me._key_3.Name = "_key_3"
        Me._key_3.Size = New System.Drawing.Size(20, 97)
        Me._key_3.TabIndex = 11
        Me._key_3.Text = "D"
        Me._key_3.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_3.UseVisualStyleBackColor = False
        '
        '_key_1
        '
        Me._key_1.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_1.BackColor = System.Drawing.Color.Black
        Me._key_1.ForeColor = System.Drawing.Color.White
        Me._key_1.Location = New System.Drawing.Point(58, 0)
        Me._key_1.Name = "_key_1"
        Me._key_1.Size = New System.Drawing.Size(20, 97)
        Me._key_1.TabIndex = 10
        Me._key_1.Text = "S"
        Me._key_1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_1.UseVisualStyleBackColor = False
        '
        '_key_16
        '
        Me._key_16.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_16.BackColor = System.Drawing.Color.White
        Me._key_16.ForeColor = System.Drawing.Color.Black
        Me._key_16.Location = New System.Drawing.Point(374, 0)
        Me._key_16.Name = "_key_16"
        Me._key_16.Size = New System.Drawing.Size(40, 158)
        Me._key_16.TabIndex = 9
        Me._key_16.Text = "/"
        Me._key_16.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_16.UseVisualStyleBackColor = False
        '
        '_key_14
        '
        Me._key_14.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_14.BackColor = System.Drawing.Color.White
        Me._key_14.ForeColor = System.Drawing.Color.Black
        Me._key_14.Location = New System.Drawing.Point(336, 0)
        Me._key_14.Name = "_key_14"
        Me._key_14.Size = New System.Drawing.Size(40, 158)
        Me._key_14.TabIndex = 8
        Me._key_14.Text = "."
        Me._key_14.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_14.UseVisualStyleBackColor = False
        '
        '_key_12
        '
        Me._key_12.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_12.BackColor = System.Drawing.Color.White
        Me._key_12.ForeColor = System.Drawing.Color.Black
        Me._key_12.Location = New System.Drawing.Point(298, 0)
        Me._key_12.Name = "_key_12"
        Me._key_12.Size = New System.Drawing.Size(39, 158)
        Me._key_12.TabIndex = 7
        Me._key_12.Text = ","
        Me._key_12.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_12.UseVisualStyleBackColor = False
        '
        '_key_11
        '
        Me._key_11.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_11.BackColor = System.Drawing.Color.White
        Me._key_11.ForeColor = System.Drawing.Color.Black
        Me._key_11.Location = New System.Drawing.Point(259, 0)
        Me._key_11.Name = "_key_11"
        Me._key_11.Size = New System.Drawing.Size(40, 158)
        Me._key_11.TabIndex = 6
        Me._key_11.Text = "M"
        Me._key_11.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_11.UseVisualStyleBackColor = False
        '
        '_key_9
        '
        Me._key_9.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_9.BackColor = System.Drawing.Color.White
        Me._key_9.ForeColor = System.Drawing.Color.Black
        Me._key_9.Location = New System.Drawing.Point(221, 0)
        Me._key_9.Name = "_key_9"
        Me._key_9.Size = New System.Drawing.Size(39, 158)
        Me._key_9.TabIndex = 5
        Me._key_9.Text = "N"
        Me._key_9.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_9.UseVisualStyleBackColor = False
        '
        '_key_7
        '
        Me._key_7.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_7.BackColor = System.Drawing.Color.White
        Me._key_7.ForeColor = System.Drawing.Color.Black
        Me._key_7.Location = New System.Drawing.Point(182, 0)
        Me._key_7.Name = "_key_7"
        Me._key_7.Size = New System.Drawing.Size(40, 158)
        Me._key_7.TabIndex = 4
        Me._key_7.Text = "B"
        Me._key_7.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_7.UseVisualStyleBackColor = False
        '
        '_key_5
        '
        Me._key_5.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_5.BackColor = System.Drawing.Color.White
        Me._key_5.ForeColor = System.Drawing.Color.Black
        Me._key_5.Location = New System.Drawing.Point(144, 0)
        Me._key_5.Name = "_key_5"
        Me._key_5.Size = New System.Drawing.Size(40, 158)
        Me._key_5.TabIndex = 3
        Me._key_5.Text = "V"
        Me._key_5.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_5.UseVisualStyleBackColor = False
        '
        '_key_4
        '
        Me._key_4.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_4.BackColor = System.Drawing.Color.White
        Me._key_4.ForeColor = System.Drawing.Color.Black
        Me._key_4.Location = New System.Drawing.Point(106, 0)
        Me._key_4.Name = "_key_4"
        Me._key_4.Size = New System.Drawing.Size(39, 158)
        Me._key_4.TabIndex = 2
        Me._key_4.Text = "C"
        Me._key_4.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_4.UseVisualStyleBackColor = False
        '
        '_key_2
        '
        Me._key_2.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_2.BackColor = System.Drawing.Color.White
        Me._key_2.ForeColor = System.Drawing.Color.Black
        Me._key_2.Location = New System.Drawing.Point(67, 0)
        Me._key_2.Name = "_key_2"
        Me._key_2.Size = New System.Drawing.Size(40, 158)
        Me._key_2.TabIndex = 1
        Me._key_2.Text = "X"
        Me._key_2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_2.UseVisualStyleBackColor = False
        '
        '_key_0
        '
        Me._key_0.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_0.BackColor = System.Drawing.Color.White
        Me._key_0.ForeColor = System.Drawing.Color.Black
        Me._key_0.Location = New System.Drawing.Point(29, 0)
        Me._key_0.Name = "_key_0"
        Me._key_0.Size = New System.Drawing.Size(39, 158)
        Me._key_0.TabIndex = 0
        Me._key_0.Text = "Z"
        Me._key_0.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_0.UseVisualStyleBackColor = False
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(0, 9)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(26, 17)
        Me.Label1.TabIndex = 18
        Me.Label1.Text = "vol"
        '
        '_chan_0
        '
        Me._chan_0.Index = 0
        Me._chan_0.Text = "1"
        '
        '_chan_1
        '
        Me._chan_1.Index = 1
        Me._chan_1.Text = "2"
        '
        '_chan_2
        '
        Me._chan_2.Index = 2
        Me._chan_2.Text = "3"
        '
        '_chan_3
        '
        Me._chan_3.Index = 3
        Me._chan_3.Text = "4"
        '
        '_chan_4
        '
        Me._chan_4.Index = 4
        Me._chan_4.Text = "5"
        '
        '_chan_5
        '
        Me._chan_5.Index = 5
        Me._chan_5.Text = "6"
        '
        '_chan_6
        '
        Me._chan_6.Index = 6
        Me._chan_6.Text = "7"
        '
        '_chan_7
        '
        Me._chan_7.Index = 7
        Me._chan_7.Text = "8"
        '
        '_chan_8
        '
        Me._chan_8.Index = 8
        Me._chan_8.Text = "9"
        '
        '_chan_9
        '
        Me._chan_9.Index = 9
        Me._chan_9.Text = "10"
        '
        '_chan_10
        '
        Me._chan_10.Index = 10
        Me._chan_10.Text = "11"
        '
        '_chan_11
        '
        Me._chan_11.Index = 11
        Me._chan_11.Text = "12"
        '
        '_chan_12
        '
        Me._chan_12.Index = 12
        Me._chan_12.Text = "13"
        '
        '_chan_13
        '
        Me._chan_13.Index = 13
        Me._chan_13.Text = "14"
        '
        '_chan_14
        '
        Me._chan_14.Index = 14
        Me._chan_14.Text = "15"
        '
        '_chan_15
        '
        Me._chan_15.Index = 15
        Me._chan_15.Text = "16"
        '
        '_device_0
        '
        Me._device_0.Index = 0
        Me._device_0.Text = ""
        '
        '_device_1
        '
        Me._device_1.Enabled = False
        Me._device_1.Index = 1
        Me._device_1.Text = ""
        Me._device_1.Visible = False
        '
        '_device_2
        '
        Me._device_2.Enabled = False
        Me._device_2.Index = 2
        Me._device_2.Text = ""
        Me._device_2.Visible = False
        '
        '_device_3
        '
        Me._device_3.Enabled = False
        Me._device_3.Index = 3
        Me._device_3.Text = ""
        Me._device_3.Visible = False
        '
        '_device_4
        '
        Me._device_4.Enabled = False
        Me._device_4.Index = 4
        Me._device_4.Text = ""
        Me._device_4.Visible = False
        '
        '_device_5
        '
        Me._device_5.Enabled = False
        Me._device_5.Index = 5
        Me._device_5.Text = ""
        Me._device_5.Visible = False
        '
        '_device_6
        '
        Me._device_6.Enabled = False
        Me._device_6.Index = 6
        Me._device_6.Text = ""
        Me._device_6.Visible = False
        '
        '_device_7
        '
        Me._device_7.Enabled = False
        Me._device_7.Index = 7
        Me._device_7.Text = ""
        Me._device_7.Visible = False
        '
        '_device_8
        '
        Me._device_8.Enabled = False
        Me._device_8.Index = 8
        Me._device_8.Text = ""
        Me._device_8.Visible = False
        '
        '_device_9
        '
        Me._device_9.Enabled = False
        Me._device_9.Index = 9
        Me._device_9.Text = ""
        Me._device_9.Visible = False
        '
        '_device_10
        '
        Me._device_10.Enabled = False
        Me._device_10.Index = 10
        Me._device_10.Text = ""
        Me._device_10.Visible = False
        '
        'MainMenu1
        '
        Me.MainMenu1.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.midi_devices, Me.ChannelOption, Me.base})
        '
        'midi_devices
        '
        Me.midi_devices.Index = 0
        Me.midi_devices.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me._device_0, Me._device_1, Me._device_2, Me._device_3, Me._device_4, Me._device_5, Me._device_6, Me._device_7, Me._device_8, Me._device_9, Me._device_10})
        Me.midi_devices.Text = "Midi Device"
        '
        'ChannelOption
        '
        Me.ChannelOption.Index = 1
        Me.ChannelOption.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me._chan_0, Me._chan_1, Me._chan_2, Me._chan_3, Me._chan_4, Me._chan_5, Me._chan_6, Me._chan_7, Me._chan_8, Me._chan_9, Me._chan_10, Me._chan_11, Me._chan_12, Me._chan_13, Me._chan_14, Me._chan_15})
        Me.ChannelOption.Text = "Channel"
        '
        'base
        '
        Me.base.Index = 2
        Me.base.Text = "Base note"
        '
        'Frame1
        '
        Me.Frame1.Controls.Add(Me.Label4)
        Me.Frame1.Controls.Add(Me.Label3)
        Me.Frame1.Controls.Add(Me.Label2)
        Me.Frame1.Controls.Add(Me.tbSpeed)
        Me.Frame1.Controls.Add(Me.btnRemoveSilence)
        Me.Frame1.Controls.Add(Me.btnPlay)
        Me.Frame1.Controls.Add(Me.btnStop)
        Me.Frame1.Controls.Add(Me.btnRecord)
        Me.Frame1.Location = New System.Drawing.Point(10, 295)
        Me.Frame1.Name = "Frame1"
        Me.Frame1.Size = New System.Drawing.Size(403, 145)
        Me.Frame1.TabIndex = 20
        Me.Frame1.TabStop = False
        Me.Frame1.Text = "MIDI Sequencer"
        '
        'Label4
        '
        Me.Label4.Location = New System.Drawing.Point(355, 99)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(39, 27)
        Me.Label4.TabIndex = 27
        Me.Label4.Text = "Fast"
        '
        'Label3
        '
        Me.Label3.Location = New System.Drawing.Point(259, 99)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(58, 37)
        Me.Label3.TabIndex = 26
        Me.Label3.Text = "Normal Speed"
        '
        'Label2
        '
        Me.Label2.Location = New System.Drawing.Point(174, 99)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(38, 27)
        Me.Label2.TabIndex = 25
        Me.Label2.Text = "Slow"
        '
        'tbSpeed
        '
        Me.tbSpeed.Location = New System.Drawing.Point(182, 63)
        Me.tbSpeed.Maximum = 9
        Me.tbSpeed.Minimum = -9
        Me.tbSpeed.Name = "tbSpeed"
        Me.tbSpeed.Size = New System.Drawing.Size(202, 56)
        Me.tbSpeed.TabIndex = 24
        '
        'btnRemoveSilence
        '
        Me.btnRemoveSilence.Location = New System.Drawing.Point(19, 63)
        Me.btnRemoveSilence.Name = "btnRemoveSilence"
        Me.btnRemoveSilence.Size = New System.Drawing.Size(154, 29)
        Me.btnRemoveSilence.TabIndex = 23
        Me.btnRemoveSilence.Text = "Remove Start Silence"
        '
        'btnPlay
        '
        Me.btnPlay.Location = New System.Drawing.Point(270, 28)
        Me.btnPlay.Name = "btnPlay"
        Me.btnPlay.Size = New System.Drawing.Size(114, 29)
        Me.btnPlay.TabIndex = 22
        Me.btnPlay.Text = "Play"
        '
        'btnStop
        '
        Me.btnStop.Location = New System.Drawing.Point(145, 28)
        Me.btnStop.Name = "btnStop"
        Me.btnStop.Size = New System.Drawing.Size(114, 29)
        Me.btnStop.TabIndex = 21
        Me.btnStop.Text = "Stop"
        '
        'btnRecord
        '
        Me.btnRecord.Location = New System.Drawing.Point(19, 28)
        Me.btnRecord.Name = "btnRecord"
        Me.btnRecord.Size = New System.Drawing.Size(114, 29)
        Me.btnRecord.TabIndex = 20
        Me.btnRecord.Text = "Record"
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.tbBankMSB)
        Me.GroupBox1.Controls.Add(Me.tbInstrument)
        Me.GroupBox1.Controls.Add(Me.Label5)
        Me.GroupBox1.Controls.Add(Me.Label6)
        Me.GroupBox1.Location = New System.Drawing.Point(10, 175)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(403, 111)
        Me.GroupBox1.TabIndex = 22
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "MIDI Instrument"
        '
        'tbBankMSB
        '
        Me.tbBankMSB.LargeChange = 2
        Me.tbBankMSB.Location = New System.Drawing.Point(77, 55)
        Me.tbBankMSB.Maximum = 8
        Me.tbBankMSB.Name = "tbBankMSB"
        Me.tbBankMSB.Size = New System.Drawing.Size(317, 56)
        Me.tbBankMSB.TabIndex = 3
        '
        'tbInstrument
        '
        Me.tbInstrument.Location = New System.Drawing.Point(77, 18)
        Me.tbInstrument.Maximum = 127
        Me.tbInstrument.Name = "tbInstrument"
        Me.tbInstrument.Size = New System.Drawing.Size(317, 56)
        Me.tbInstrument.TabIndex = 2
        '
        'Label5
        '
        Me.Label5.Location = New System.Drawing.Point(10, 55)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(120, 27)
        Me.Label5.TabIndex = 1
        Me.Label5.Text = "Bank MSB"
        '
        'Label6
        '
        Me.Label6.Location = New System.Drawing.Point(10, 28)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(76, 26)
        Me.Label6.TabIndex = 0
        Me.Label6.Text = "Instrument"
        '
        'tmrSequencer
        '
        '
        'txtStatus
        '
        Me.txtStatus.AutoSize = True
        Me.txtStatus.Location = New System.Drawing.Point(23, 447)
        Me.txtStatus.Name = "txtStatus"
        Me.txtStatus.Size = New System.Drawing.Size(107, 17)
        Me.txtStatus.TabIndex = 23
        Me.txtStatus.Text = "status message"
        '
        'txtMidiSignal
        '
        Me.txtMidiSignal.AutoSize = True
        Me.txtMidiSignal.Location = New System.Drawing.Point(23, 475)
        Me.txtMidiSignal.Name = "txtMidiSignal"
        Me.txtMidiSignal.Size = New System.Drawing.Size(74, 17)
        Me.txtMidiSignal.TabIndex = 23
        Me.txtMidiSignal.Text = "midi signal"
        '
        'Form1
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(566, 501)
        Me.Controls.Add(Me.txtMidiSignal)
        Me.Controls.Add(Me.txtStatus)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.Frame1)
        Me.Controls.Add(Me.vol)
        Me.Controls.Add(Me._key_15)
        Me.Controls.Add(Me._key_13)
        Me.Controls.Add(Me._key_10)
        Me.Controls.Add(Me._key_8)
        Me.Controls.Add(Me._key_6)
        Me.Controls.Add(Me._key_3)
        Me.Controls.Add(Me._key_1)
        Me.Controls.Add(Me._key_16)
        Me.Controls.Add(Me._key_14)
        Me.Controls.Add(Me._key_12)
        Me.Controls.Add(Me._key_11)
        Me.Controls.Add(Me._key_9)
        Me.Controls.Add(Me._key_7)
        Me.Controls.Add(Me._key_5)
        Me.Controls.Add(Me._key_4)
        Me.Controls.Add(Me._key_2)
        Me.Controls.Add(Me._key_0)
        Me.Controls.Add(Me.Label1)
        Me.KeyPreview = True
        Me.Location = New System.Drawing.Point(11, 49)
        Me.Menu = Me.MainMenu1
        Me.Name = "Form1"
        Me.Text = "VB Midi Piano"
        Me.Frame1.ResumeLayout(False)
        Me.Frame1.PerformLayout()
        CType(Me.tbSpeed, System.ComponentModel.ISupportInitialize).EndInit()
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        CType(Me.tbBankMSB, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbInstrument, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
#End Region

    Const INVALID_NOTE As Short = -1 ' Code for keyboard keys that we don't handle

    Dim numDevices As Integer ' number of midi output devices
    Dim curDevice As Integer ' current midi device
    Dim hmidi As Integer ' midi output handle
    Dim rc As Integer ' return code
    Dim midimsg As Integer ' midi output message buffer
    Dim channel As Short ' midi output channel
    Dim volume As Short ' midi volume
    Dim baseNote As Short ' the first note on our "piano"
    Dim playChord As Boolean
    Dim speed As Double = 1

    Dim key As CheckBoxArray ' an array of check box for keys
    Dim chan As MenuItemArray ' an array of menu item for channel
    Dim device As MenuItemArray ' an array of menu item for midi device

    ' for recording
    Dim isRecording As Boolean ' recording status
    Dim startTime As System.DateTime ' the time of starting recording
    Dim midiSequence As SequenceData ' store MIDI sequence
    Dim currentIndex As Integer ' store the current playing sequence index


#Region "function initControlArray()"


    Public Sub initControlArray()
        ' initialize key checkboxarray (not generated by vb)
        key = New CheckBoxArray

        AddHandler _key_0.MouseDown, AddressOf key_MouseDown
        AddHandler _key_0.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_0)

        AddHandler _key_1.MouseDown, AddressOf key_MouseDown
        AddHandler _key_1.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_1)

        AddHandler _key_2.MouseDown, AddressOf key_MouseDown
        AddHandler _key_2.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_2)

        AddHandler _key_3.MouseDown, AddressOf key_MouseDown
        AddHandler _key_3.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_3)

        AddHandler _key_4.MouseDown, AddressOf key_MouseDown
        AddHandler _key_4.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_4)

        AddHandler _key_5.MouseDown, AddressOf key_MouseDown
        AddHandler _key_5.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_5)

        AddHandler _key_6.MouseDown, AddressOf key_MouseDown
        AddHandler _key_6.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_6)

        AddHandler _key_7.MouseDown, AddressOf key_MouseDown
        AddHandler _key_7.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_7)

        AddHandler _key_8.MouseDown, AddressOf key_MouseDown
        AddHandler _key_8.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_8)

        AddHandler _key_9.MouseDown, AddressOf key_MouseDown
        AddHandler _key_9.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_9)

        AddHandler _key_10.MouseDown, AddressOf key_MouseDown
        AddHandler _key_10.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_10)

        AddHandler _key_11.MouseDown, AddressOf key_MouseDown
        AddHandler _key_11.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_11)

        AddHandler _key_12.MouseDown, AddressOf key_MouseDown
        AddHandler _key_12.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_12)

        AddHandler _key_13.MouseDown, AddressOf key_MouseDown
        AddHandler _key_13.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_13)

        AddHandler _key_14.MouseDown, AddressOf key_MouseDown
        AddHandler _key_14.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_14)

        AddHandler _key_15.MouseDown, AddressOf key_MouseDown
        AddHandler _key_15.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_15)

        AddHandler _key_16.MouseDown, AddressOf key_MouseDown
        AddHandler _key_16.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_16)

        ' initialize chan menuitemarray (not generated by vb)
        chan = New MenuItemArray
        AddHandler _chan_0.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_0)

        AddHandler _chan_1.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_1)

        AddHandler _chan_2.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_2)

        AddHandler _chan_3.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_3)

        AddHandler _chan_4.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_4)

        AddHandler _chan_5.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_5)

        AddHandler _chan_6.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_6)

        AddHandler _chan_7.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_7)

        AddHandler _chan_8.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_8)

        AddHandler _chan_9.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_9)

        AddHandler _chan_10.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_10)

        AddHandler _chan_11.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_11)

        AddHandler _chan_12.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_12)

        AddHandler _chan_13.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_13)

        AddHandler _chan_14.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_14)

        AddHandler _chan_15.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_15)

        ' initialize device menuitemarray (not generated by vb)
        device = New MenuItemArray

        AddHandler _device_0.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_0)

        AddHandler _device_1.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_1)

        AddHandler _device_2.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_2)

        AddHandler _device_3.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_3)

        AddHandler _device_4.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_4)

        AddHandler _device_5.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_5)

        AddHandler _device_6.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_6)

        AddHandler _device_7.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_7)

        AddHandler _device_8.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_8)

        AddHandler _device_9.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_9)

        AddHandler _device_10.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_10)
    End Sub
#End Region
    Private Sub generateMidiSignal(ByVal signal As Integer)
        midiOutShortMsg(hmidi, signal)

        ' 2.1 Start recording a MIDI sequence
        ' Store the message
        If isRecording Then
            midiSequence.AddSequenceData(midimsg, DateTime.Now.Subtract(startTime))
            txtStatus.Text = "Recorded " + midiSequence.dataLength.ToString() + " midi signals"
        End If

    End Sub

    Private Sub generateMidiSignal(ByVal byte1 As Integer, ByVal byte2 As Integer, ByVal byte3 As Integer)
        midimsg = byte1 + byte2 * &H100 + byte3 * &H10000
        midiOutShortMsg(hmidi, midimsg)

        ' 2.1 Start recording a MIDI sequence
        ' Store the message
        If isRecording Then
            midiSequence.AddSequenceData(midimsg, DateTime.Now.Subtract(startTime))
            txtStatus.Text = "Recorded " + midiSequence.dataLength.ToString() + " midi signals"
        End If

        ' Display midi signal for debugging.
        midimsg = byte1 * &H10000 + byte2 * &H100 + byte3
        Dim i As Long
        Dim bin As String
        If midimsg < 0 Then bin = bin + "1" Else bin = bin + "0"
        For i = 23 To 0 Step -1
            If midimsg And (2 ^ i) Then   ' Use the logical "AND" operator.
                bin = bin + "1"
            Else
                bin = bin + "0"
            End If
            If (i Mod 8 = 0) Then
                bin += " "
            End If
        Next
        txtMidiSignal.Text = bin
    End Sub

    ' Set the value for the starting note of the piano
    Public Sub base_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles base.Click
        Dim s As String
        Dim i As Short
        s = InputBox("Enter the new base note for the keyboard (0 - 111)", "Base note", CStr(baseNote))
        If IsNumeric(s) Then
            i = CShort(s)
            If (i >= 0 And i < 112) Then
                baseNote = i
            End If
        End If
    End Sub

    ' Select the midi output channel
    Public Sub chan_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs)
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.MenuItem).Index
        chan(channel).Checked = False
        channel = index
        chan(channel).Checked = True
    End Sub

    ' Open the midi device selected in the menu. The menu index equals the midi device number + 1.
    Public Sub device_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs)
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.MenuItem).Index
        device(curDevice + 1).Checked = False
        device(index).Checked = True
        curDevice = index - 1
        rc = midiOutClose(hmidi)
        rc = midiOutOpen(hmidi, curDevice, 0, 0, 0)
        If (rc <> 0) Then
            MessageBox.Show("Couldn't open midi out, rc = " & rc, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If
    End Sub

    ' If user presses a keyboard key, start the corresponding midi note
    Private Sub Form1_KeyDown(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyDown
        Dim KeyCode As Short = eventArgs.KeyCode

        StartNote(NoteFromKey(KeyCode))
    End Sub

    ' If user lifts a keyboard key, stop the corresponding midi note
    Private Sub Form1_KeyUp(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyUp
        Dim KeyCode As Short = eventArgs.KeyCode

        StopNote(NoteFromKey(KeyCode))
    End Sub

    Private Sub Form1_Load(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles MyBase.Load
        Dim i As Integer
        Dim caps As MIDIOUTCAPS

        Try
            initControlArray()

            ' Set the first device as midi mapper
            device(0).Text = "MIDI Mapper"
            device(0).Visible = True
            device(0).Enabled = True

            ' Get the rest of the midi devices
            numDevices = midiOutGetNumDevs()
            For i = 0 To (numDevices - 1)
                midiOutGetDevCaps(i, caps, Len(caps))
                device(i + 1).Text = caps.szPname
                device(i + 1).Visible = True
                device(i + 1).Enabled = True
            Next

            ' Select the MIDI Mapper as the default device
            device_Click(device.Item(0), New System.EventArgs)

            ' Set the default channel
            channel = 0
            chan(channel).Checked = True

            ' Set the base note
            baseNote = 60

            ' Set volume range
            volume = 127
            vol.Minimum = (0 + vol.LargeChange - 1)
            vol.Maximum = 127
            vol.Value = vol.Maximum - volume

            midiSequence = Nothing
            isRecording = False

            btnStop.Enabled = False

        Catch ex As Exception

        End Try
    End Sub

    Private Sub Form1_Closed(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles MyBase.Closed
        ' Close current midi device
        rc = midiOutClose(hmidi)
    End Sub

    ' Start a note when user click on it
    Public Sub key_MouseDown(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.MouseEventArgs)
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.CheckBox).Tag
        StartNote((index))
    End Sub

    ' Stop the note when user lifts the mouse button
    Public Sub key_MouseUp(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.MouseEventArgs)
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.CheckBox).Tag
        StopNote((index))
    End Sub

    ' Press the button and send midi start event
    Private Sub StartNote(ByRef Index As Short)
        If (Index = INVALID_NOTE) Then
            Exit Sub
        End If
        If (key(Index).CheckState = 1) Then
            Exit Sub
        End If

        key(Index).CheckState = System.Windows.Forms.CheckState.Checked
        generateMidiSignal(&H90 + channel, baseNote + Index, volume)
    End Sub

    ' Raise the button and send midi stop event
    Private Sub StopNote(ByRef Index As Short)
        If (Index = INVALID_NOTE) Then
            Exit Sub
        End If

        key(Index).CheckState = System.Windows.Forms.CheckState.Unchecked
        generateMidiSignal(&H80 + channel, baseNote + Index, volume)
    End Sub

    ' Get the note corresponding to a keyboard key
    Private Function NoteFromKey(ByRef key As Short) As Short

        NoteFromKey = INVALID_NOTE

        Select Case key
            Case System.Windows.Forms.Keys.Z
                NoteFromKey = 0
            Case System.Windows.Forms.Keys.S
                NoteFromKey = 1
            Case System.Windows.Forms.Keys.X
                NoteFromKey = 2
            Case System.Windows.Forms.Keys.D
                NoteFromKey = 3
            Case System.Windows.Forms.Keys.C
                NoteFromKey = 4
            Case System.Windows.Forms.Keys.V
                NoteFromKey = 5
            Case System.Windows.Forms.Keys.G
                NoteFromKey = 6
            Case System.Windows.Forms.Keys.B
                NoteFromKey = 7
            Case System.Windows.Forms.Keys.H
                NoteFromKey = 8
            Case System.Windows.Forms.Keys.N
                NoteFromKey = 9
            Case System.Windows.Forms.Keys.J
                NoteFromKey = 10
            Case System.Windows.Forms.Keys.M
                NoteFromKey = 11
            Case 188 ' comma
                NoteFromKey = 12
            Case System.Windows.Forms.Keys.L
                NoteFromKey = 13
            Case 190 ' period
                NoteFromKey = 14
            Case 186 ' semicolon
                NoteFromKey = 15
            Case 191 ' forward slash
                NoteFromKey = 16
        End Select

    End Function

    ' Set the volume
    Private Sub vol_Scroll(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.ScrollEventArgs) Handles vol.Scroll
        Select Case eventArgs.Type
            Case System.Windows.Forms.ScrollEventType.EndScroll
                volume = vol.Maximum - eventArgs.NewValue
        End Select
    End Sub

    ' Start recording MIDI sequence
    Private Sub btnRecord_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles btnRecord.Click
        ' 2.1 Start recording a MIDI sequence
        ' Initialize the sequence data
        isRecording = True
        startTime = DateTime.Now
        midiSequence = Nothing
        midiSequence = New SequenceData
        ' Send the messages for the instrument so that the playback will match
        generateMidiSignal(&HC0 + channel, tbInstrument.Value, 0)

        txtStatus.Text = "Start recording.."
        btnRecord.Enabled = False
        btnPlay.Enabled = False
        btnStop.Enabled = True
    End Sub

    ' Stop recording MIDI sequence
    Private Sub btnStop_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles btnStop.Click
        ' 2.2 Stop recording a MIDI sequence
        ' Stop the recording
        isRecording = False
        ' 2.3 Play a MIDI sequence
        ' Stop the playback
        txtStatus.Text = "recording stopped"
        tmrSequencer.Enabled = False
        btnRecord.Enabled = True
        btnPlay.Enabled = True
        btnStop.Enabled = False
    End Sub

    ' Play the MIDI sequence recorded
    Private Sub btnPlay_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles btnPlay.Click
        If midiSequence Is Nothing Then
            Exit Sub
        End If

        ' 2.3 Play a MIDI sequence
        ' Start the timer using the time of the first sequencer message data
        currentIndex = 0
        tmrSequencer.Interval = CInt(midiSequence.data(0).time.TotalMilliseconds * speed) + 1
        tmrSequencer.Enabled = True
        btnRecord.Enabled = False
        btnPlay.Enabled = False
        btnStop.Enabled = True
        txtStatus.Text = "playback started"
    End Sub

    Private Sub tmrSequencer_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tmrSequencer.Tick
        ' 2.3 Play a MIDI sequence
        ' Send the MIDI message of the current message and schedule the next one
        'txtStatus.Text = "playback midi signal sent " + currentIndex.ToString() + " currentTime = " + midiSequence.data(currentIndex).time.ToString()
        tmrSequencer.Enabled = False

        Dim currentTime As Double
        currentTime = midiSequence.data(currentIndex).time.TotalMilliseconds
        While (currentTime >= midiSequence.data(currentIndex).time.TotalMilliseconds)
            txtStatus.Text = "playback midi signal sent " + currentIndex.ToString() + " currentTime = " + midiSequence.data(currentIndex).time.ToString()
            generateMidiSignal(midiSequence.data(currentIndex).midiMsg)
            currentIndex += 1
            If currentIndex = midiSequence.dataLength Then
                Exit While
            End If
            'txtStatus.Text = "pp "
        End While

        If currentIndex < midiSequence.dataLength Then
            tmrSequencer.Interval = CInt((midiSequence.data(currentIndex).time.TotalMilliseconds - midiSequence.data(currentIndex - 1).time.TotalMilliseconds) * speed) + 1
            txtStatus.Text = "interval set to " + tmrSequencer.Interval.ToString() + " ms"
            tmrSequencer.Enabled = True
            'txtStatus.Text = "more " + currentIndex.ToString()
        Else
            btnRecord.Enabled = True
            btnPlay.Enabled = True
            btnStop.Enabled = False
            btnRemoveSilence.Enabled = True
        End If


    End Sub

   

    Private Sub tbSpeed_Scroll(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tbSpeed.Scroll
        If tbSpeed.Value > 0 Then
            speed = 1 - tbSpeed.Value / 10.0
        Else
            speed = 1 - tbSpeed.Value / 10.0
        End If
    End Sub

    Private Sub btnRemoveSilence_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnRemoveSilence.Click
        If midiSequence Is Nothing Then
            Exit Sub
        End If

        ' 3.1 Remove silence at the start
        ' Get first 
        Dim firstNoteOnSignalIndex As Integer
        Dim firstNoteOnSignalTime As System.TimeSpan
        Dim i As Integer
        For i = 0 To UBound(midiSequence.data)
            If (midiSequence.data(i).midiMsg) >> 4 Or &HF Then
                firstNoteOnSignalIndex = i
                firstNoteOnSignalTime = midiSequence.data(i).time
            End If
        Next
        txtStatus.Text = "start of the note = " + firstNoteOnSignalTime.ToString

        For i = firstNoteOnSignalIndex To UBound(midiSequence.data)
            midiSequence.data(i).time.Subtract(firstNoteOnSignalTime)
        Next

    End Sub

    ' Change the instrument by sending a program change message
    Private Sub tbInstrument_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tbInstrument.ValueChanged
        generateMidiSignal(&HC0 + channel, tbInstrument.Value, 0)
        txtStatus.Text = "tbInstrument_ValueChanged to " + tbInstrument.Value.ToString()
    End Sub

    Private Sub tbBankMSB_ValueChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tbBankMSB.ValueChanged
        ' 1. Multiple Banks
        ' Send a control change message to change the bank
        ' Resend a program change message for the instrument so that it comes into effect immediately
        txtStatus.Text = "tbBankMSB_ValueChanged to " + tbBankMSB.Value.ToString()
        generateMidiSignal(&HB0 + channel, &H0, tbBankMSB.Value)
    End Sub

End Class
