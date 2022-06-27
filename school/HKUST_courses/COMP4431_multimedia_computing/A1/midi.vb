''
'   COMP4431 Assignment 1
'
'   CHOI, Hong Joon
'   20161472
'
'   Lab session: There is only one lab session!
'
'
'
'









Option Explicit On
Public Class frmMidiPiano
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
    Private WithEvents vsbVolume As System.Windows.Forms.VScrollBar
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
    Private WithEvents lblVolume As System.Windows.Forms.Label
    Public WithEvents mnuDevice0 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice1 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice2 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice3 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice4 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice5 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice6 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice7 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice8 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice9 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice10 As System.Windows.Forms.MenuItem
    Public WithEvents mnuDevice As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel0 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel1 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel2 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel3 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel4 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel5 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel6 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel7 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel8 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel9 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel10 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel11 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel12 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel13 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel14 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel15 As System.Windows.Forms.MenuItem
    Public WithEvents mnuChannel As System.Windows.Forms.MenuItem
    Public WithEvents mnuBaseNote As System.Windows.Forms.MenuItem
    Public mnuMain As System.Windows.Forms.MainMenu
    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.
    'Do not modify it using the code editor.
    Private WithEvents btnPlay As System.Windows.Forms.Button
    Private WithEvents btnStop As System.Windows.Forms.Button
    Private WithEvents btnRecord As System.Windows.Forms.Button
    Private WithEvents btnRemoveSilence As System.Windows.Forms.Button
    Friend WithEvents tbSpeed As System.Windows.Forms.TrackBar
    Friend WithEvents lblSeqSlow As System.Windows.Forms.Label
    Friend WithEvents lblSeqNormal As System.Windows.Forms.Label
    Friend WithEvents lblSeqFast As System.Windows.Forms.Label
    Friend WithEvents gbxInstrument As System.Windows.Forms.GroupBox
    Friend WithEvents tbBankMSB As System.Windows.Forms.TrackBar
    Friend WithEvents tbInstrument As System.Windows.Forms.TrackBar
    Friend WithEvents lblBankMSB As System.Windows.Forms.Label
    Friend WithEvents tmrSequencer As System.Windows.Forms.Timer
    Friend WithEvents tclMidiFunction As System.Windows.Forms.TabControl
    Friend WithEvents tabSequencer As System.Windows.Forms.TabPage
    Friend WithEvents tabDrumMachine As System.Windows.Forms.TabPage
    Friend WithEvents tabWhiteboard As System.Windows.Forms.TabPage
    Friend WithEvents gbxXAxis As System.Windows.Forms.GroupBox
    Friend WithEvents lblXValue As System.Windows.Forms.Label
    Friend WithEvents cboXTitle As System.Windows.Forms.ComboBox
    Friend WithEvents lblXTitle As System.Windows.Forms.Label
    Friend WithEvents lblXCaption As System.Windows.Forms.Label
    Friend WithEvents picWhiteboard As System.Windows.Forms.PictureBox
    Friend WithEvents gbxYAxis As System.Windows.Forms.GroupBox
    Friend WithEvents cboYTitle As System.Windows.Forms.ComboBox
    Friend WithEvents lblYTitle As System.Windows.Forms.Label
    Friend WithEvents lblYCaption As System.Windows.Forms.Label
    Friend WithEvents lblYValue As System.Windows.Forms.Label
    Public WithEvents btnDrumStop As System.Windows.Forms.Button
    Public WithEvents btnDrumStart As System.Windows.Forms.Button
    Public WithEvents picDrum As System.Windows.Forms.PictureBox
    Friend WithEvents mnuFile As System.Windows.Forms.MenuItem
    Friend WithEvents mnuOpen As System.Windows.Forms.MenuItem
    Public WithEvents tmrDrumPlayback As System.Windows.Forms.Timer
    Friend WithEvents lblStereo As System.Windows.Forms.Label
    Friend WithEvents tBarStereo As System.Windows.Forms.TrackBar
    Friend WithEvents txtStatus As System.Windows.Forms.Label
    Friend WithEvents txtMidiSignal As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents cBoxChordMode As System.Windows.Forms.ComboBox
    Friend WithEvents btnReset As System.Windows.Forms.Button
    Friend WithEvents btnLoop As System.Windows.Forms.Button
    Friend WithEvents tbTranspose As System.Windows.Forms.TrackBar
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents btnAppend As System.Windows.Forms.Button
    Friend WithEvents btnAddRow As System.Windows.Forms.Button
    Friend WithEvents btnRemoveRow As System.Windows.Forms.Button
    Friend WithEvents btnRemoveCol As System.Windows.Forms.Button
    Friend WithEvents btnAddCol As System.Windows.Forms.Button
    Friend WithEvents BtnRandomDrum As System.Windows.Forms.Button
    Friend WithEvents tbDrumSpeed As System.Windows.Forms.TrackBar
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents btnReverse As System.Windows.Forms.Button
    Friend WithEvents btnInverse As System.Windows.Forms.Button
    Friend WithEvents Button1 As System.Windows.Forms.Button
    Friend WithEvents btnLoad As System.Windows.Forms.Button
    Friend WithEvents btnRandomBox As System.Windows.Forms.Button
    Friend WithEvents lblInstrument As System.Windows.Forms.Label
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.vsbVolume = New System.Windows.Forms.VScrollBar()
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
        Me.lblVolume = New System.Windows.Forms.Label()
        Me.mnuChannel0 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel1 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel2 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel3 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel4 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel5 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel6 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel7 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel8 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel9 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel10 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel11 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel12 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel13 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel14 = New System.Windows.Forms.MenuItem()
        Me.mnuChannel15 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice0 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice1 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice2 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice3 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice4 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice5 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice6 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice7 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice8 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice9 = New System.Windows.Forms.MenuItem()
        Me.mnuDevice10 = New System.Windows.Forms.MenuItem()
        Me.mnuMain = New System.Windows.Forms.MainMenu(Me.components)
        Me.mnuFile = New System.Windows.Forms.MenuItem()
        Me.mnuOpen = New System.Windows.Forms.MenuItem()
        Me.mnuDevice = New System.Windows.Forms.MenuItem()
        Me.mnuChannel = New System.Windows.Forms.MenuItem()
        Me.mnuBaseNote = New System.Windows.Forms.MenuItem()
        Me.lblSeqFast = New System.Windows.Forms.Label()
        Me.lblSeqNormal = New System.Windows.Forms.Label()
        Me.lblSeqSlow = New System.Windows.Forms.Label()
        Me.tbSpeed = New System.Windows.Forms.TrackBar()
        Me.btnRemoveSilence = New System.Windows.Forms.Button()
        Me.btnPlay = New System.Windows.Forms.Button()
        Me.btnStop = New System.Windows.Forms.Button()
        Me.btnRecord = New System.Windows.Forms.Button()
        Me.gbxInstrument = New System.Windows.Forms.GroupBox()
        Me.cBoxChordMode = New System.Windows.Forms.ComboBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.tBarStereo = New System.Windows.Forms.TrackBar()
        Me.lblStereo = New System.Windows.Forms.Label()
        Me.tbBankMSB = New System.Windows.Forms.TrackBar()
        Me.tbInstrument = New System.Windows.Forms.TrackBar()
        Me.lblBankMSB = New System.Windows.Forms.Label()
        Me.lblInstrument = New System.Windows.Forms.Label()
        Me.tmrSequencer = New System.Windows.Forms.Timer(Me.components)
        Me.tclMidiFunction = New System.Windows.Forms.TabControl()
        Me.tabSequencer = New System.Windows.Forms.TabPage()
        Me.btnAppend = New System.Windows.Forms.Button()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.tbTranspose = New System.Windows.Forms.TrackBar()
        Me.btnLoop = New System.Windows.Forms.Button()
        Me.tabWhiteboard = New System.Windows.Forms.TabPage()
        Me.gbxXAxis = New System.Windows.Forms.GroupBox()
        Me.lblXValue = New System.Windows.Forms.Label()
        Me.cboXTitle = New System.Windows.Forms.ComboBox()
        Me.lblXTitle = New System.Windows.Forms.Label()
        Me.lblXCaption = New System.Windows.Forms.Label()
        Me.picWhiteboard = New System.Windows.Forms.PictureBox()
        Me.gbxYAxis = New System.Windows.Forms.GroupBox()
        Me.cboYTitle = New System.Windows.Forms.ComboBox()
        Me.lblYTitle = New System.Windows.Forms.Label()
        Me.lblYCaption = New System.Windows.Forms.Label()
        Me.lblYValue = New System.Windows.Forms.Label()
        Me.tabDrumMachine = New System.Windows.Forms.TabPage()
        Me.btnLoad = New System.Windows.Forms.Button()
        Me.Button1 = New System.Windows.Forms.Button()
        Me.btnReverse = New System.Windows.Forms.Button()
        Me.btnInverse = New System.Windows.Forms.Button()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.tbDrumSpeed = New System.Windows.Forms.TrackBar()
        Me.btnRandomBox = New System.Windows.Forms.Button()
        Me.BtnRandomDrum = New System.Windows.Forms.Button()
        Me.btnRemoveRow = New System.Windows.Forms.Button()
        Me.btnRemoveCol = New System.Windows.Forms.Button()
        Me.btnAddCol = New System.Windows.Forms.Button()
        Me.btnAddRow = New System.Windows.Forms.Button()
        Me.btnDrumStop = New System.Windows.Forms.Button()
        Me.btnDrumStart = New System.Windows.Forms.Button()
        Me.picDrum = New System.Windows.Forms.PictureBox()
        Me.tmrDrumPlayback = New System.Windows.Forms.Timer(Me.components)
        Me.txtStatus = New System.Windows.Forms.Label()
        Me.txtMidiSignal = New System.Windows.Forms.Label()
        Me.btnReset = New System.Windows.Forms.Button()
        CType(Me.tbSpeed, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbxInstrument.SuspendLayout()
        CType(Me.tBarStereo, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbBankMSB, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbInstrument, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.tclMidiFunction.SuspendLayout()
        Me.tabSequencer.SuspendLayout()
        CType(Me.tbTranspose, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.tabWhiteboard.SuspendLayout()
        Me.gbxXAxis.SuspendLayout()
        CType(Me.picWhiteboard, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbxYAxis.SuspendLayout()
        Me.tabDrumMachine.SuspendLayout()
        CType(Me.tbDrumSpeed, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.picDrum, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'vsbVolume
        '
        Me.vsbVolume.LargeChange = 1
        Me.vsbVolume.Location = New System.Drawing.Point(14, 29)
        Me.vsbVolume.Maximum = 127
        Me.vsbVolume.Name = "vsbVolume"
        Me.vsbVolume.Size = New System.Drawing.Size(60, 142)
        Me.vsbVolume.TabIndex = 17
        Me.vsbVolume.TabStop = True
        '
        '_key_15
        '
        Me._key_15.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_15.BackColor = System.Drawing.Color.Black
        Me._key_15.ForeColor = System.Drawing.Color.White
        Me._key_15.Location = New System.Drawing.Point(425, 14)
        Me._key_15.Name = "_key_15"
        Me._key_15.Size = New System.Drawing.Size(20, 96)
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
        Me._key_13.Location = New System.Drawing.Point(386, 14)
        Me._key_13.Name = "_key_13"
        Me._key_13.Size = New System.Drawing.Size(21, 96)
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
        Me._key_10.Location = New System.Drawing.Point(310, 14)
        Me._key_10.Name = "_key_10"
        Me._key_10.Size = New System.Drawing.Size(20, 96)
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
        Me._key_8.Location = New System.Drawing.Point(271, 14)
        Me._key_8.Name = "_key_8"
        Me._key_8.Size = New System.Drawing.Size(21, 96)
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
        Me._key_6.Location = New System.Drawing.Point(233, 14)
        Me._key_6.Name = "_key_6"
        Me._key_6.Size = New System.Drawing.Size(20, 96)
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
        Me._key_3.Location = New System.Drawing.Point(156, 14)
        Me._key_3.Name = "_key_3"
        Me._key_3.Size = New System.Drawing.Size(20, 96)
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
        Me._key_1.Location = New System.Drawing.Point(118, 14)
        Me._key_1.Name = "_key_1"
        Me._key_1.Size = New System.Drawing.Size(20, 96)
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
        Me._key_16.Location = New System.Drawing.Point(434, 14)
        Me._key_16.Name = "_key_16"
        Me._key_16.Size = New System.Drawing.Size(40, 157)
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
        Me._key_14.Location = New System.Drawing.Point(396, 14)
        Me._key_14.Name = "_key_14"
        Me._key_14.Size = New System.Drawing.Size(40, 157)
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
        Me._key_12.Location = New System.Drawing.Point(358, 14)
        Me._key_12.Name = "_key_12"
        Me._key_12.Size = New System.Drawing.Size(39, 157)
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
        Me._key_11.Location = New System.Drawing.Point(319, 14)
        Me._key_11.Name = "_key_11"
        Me._key_11.Size = New System.Drawing.Size(40, 157)
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
        Me._key_9.Location = New System.Drawing.Point(281, 14)
        Me._key_9.Name = "_key_9"
        Me._key_9.Size = New System.Drawing.Size(39, 157)
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
        Me._key_7.Location = New System.Drawing.Point(242, 14)
        Me._key_7.Name = "_key_7"
        Me._key_7.Size = New System.Drawing.Size(40, 157)
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
        Me._key_5.Location = New System.Drawing.Point(204, 14)
        Me._key_5.Name = "_key_5"
        Me._key_5.Size = New System.Drawing.Size(40, 157)
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
        Me._key_4.Location = New System.Drawing.Point(166, 14)
        Me._key_4.Name = "_key_4"
        Me._key_4.Size = New System.Drawing.Size(39, 157)
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
        Me._key_2.Location = New System.Drawing.Point(127, 14)
        Me._key_2.Name = "_key_2"
        Me._key_2.Size = New System.Drawing.Size(40, 157)
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
        Me._key_0.Location = New System.Drawing.Point(89, 14)
        Me._key_0.Name = "_key_0"
        Me._key_0.Size = New System.Drawing.Size(39, 157)
        Me._key_0.TabIndex = 0
        Me._key_0.Text = "Z"
        Me._key_0.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_0.UseVisualStyleBackColor = False
        '
        'lblVolume
        '
        Me.lblVolume.AutoSize = True
        Me.lblVolume.Location = New System.Drawing.Point(19, 14)
        Me.lblVolume.Name = "lblVolume"
        Me.lblVolume.Size = New System.Drawing.Size(55, 17)
        Me.lblVolume.TabIndex = 18
        Me.lblVolume.Text = "Volume"
        '
        'mnuChannel0
        '
        Me.mnuChannel0.Index = 0
        Me.mnuChannel0.Text = "1"
        '
        'mnuChannel1
        '
        Me.mnuChannel1.Index = 1
        Me.mnuChannel1.Text = "2"
        '
        'mnuChannel2
        '
        Me.mnuChannel2.Index = 2
        Me.mnuChannel2.Text = "3"
        '
        'mnuChannel3
        '
        Me.mnuChannel3.Index = 3
        Me.mnuChannel3.Text = "4"
        '
        'mnuChannel4
        '
        Me.mnuChannel4.Index = 4
        Me.mnuChannel4.Text = "5"
        '
        'mnuChannel5
        '
        Me.mnuChannel5.Index = 5
        Me.mnuChannel5.Text = "6"
        '
        'mnuChannel6
        '
        Me.mnuChannel6.Index = 6
        Me.mnuChannel6.Text = "7"
        '
        'mnuChannel7
        '
        Me.mnuChannel7.Index = 7
        Me.mnuChannel7.Text = "8"
        '
        'mnuChannel8
        '
        Me.mnuChannel8.Index = 8
        Me.mnuChannel8.Text = "9"
        '
        'mnuChannel9
        '
        Me.mnuChannel9.Index = 9
        Me.mnuChannel9.Text = "10"
        '
        'mnuChannel10
        '
        Me.mnuChannel10.Index = 10
        Me.mnuChannel10.Text = "11"
        '
        'mnuChannel11
        '
        Me.mnuChannel11.Index = 11
        Me.mnuChannel11.Text = "12"
        '
        'mnuChannel12
        '
        Me.mnuChannel12.Index = 12
        Me.mnuChannel12.Text = "13"
        '
        'mnuChannel13
        '
        Me.mnuChannel13.Index = 13
        Me.mnuChannel13.Text = "14"
        '
        'mnuChannel14
        '
        Me.mnuChannel14.Index = 14
        Me.mnuChannel14.Text = "15"
        '
        'mnuChannel15
        '
        Me.mnuChannel15.Index = 15
        Me.mnuChannel15.Text = "16"
        '
        'mnuDevice0
        '
        Me.mnuDevice0.Index = 0
        Me.mnuDevice0.Text = ""
        '
        'mnuDevice1
        '
        Me.mnuDevice1.Enabled = False
        Me.mnuDevice1.Index = 1
        Me.mnuDevice1.Text = ""
        Me.mnuDevice1.Visible = False
        '
        'mnuDevice2
        '
        Me.mnuDevice2.Enabled = False
        Me.mnuDevice2.Index = 2
        Me.mnuDevice2.Text = ""
        Me.mnuDevice2.Visible = False
        '
        'mnuDevice3
        '
        Me.mnuDevice3.Enabled = False
        Me.mnuDevice3.Index = 3
        Me.mnuDevice3.Text = ""
        Me.mnuDevice3.Visible = False
        '
        'mnuDevice4
        '
        Me.mnuDevice4.Enabled = False
        Me.mnuDevice4.Index = 4
        Me.mnuDevice4.Text = ""
        Me.mnuDevice4.Visible = False
        '
        'mnuDevice5
        '
        Me.mnuDevice5.Enabled = False
        Me.mnuDevice5.Index = 5
        Me.mnuDevice5.Text = ""
        Me.mnuDevice5.Visible = False
        '
        'mnuDevice6
        '
        Me.mnuDevice6.Enabled = False
        Me.mnuDevice6.Index = 6
        Me.mnuDevice6.Text = ""
        Me.mnuDevice6.Visible = False
        '
        'mnuDevice7
        '
        Me.mnuDevice7.Enabled = False
        Me.mnuDevice7.Index = 7
        Me.mnuDevice7.Text = ""
        Me.mnuDevice7.Visible = False
        '
        'mnuDevice8
        '
        Me.mnuDevice8.Enabled = False
        Me.mnuDevice8.Index = 8
        Me.mnuDevice8.Text = ""
        Me.mnuDevice8.Visible = False
        '
        'mnuDevice9
        '
        Me.mnuDevice9.Enabled = False
        Me.mnuDevice9.Index = 9
        Me.mnuDevice9.Text = ""
        Me.mnuDevice9.Visible = False
        '
        'mnuDevice10
        '
        Me.mnuDevice10.Enabled = False
        Me.mnuDevice10.Index = 10
        Me.mnuDevice10.Text = ""
        Me.mnuDevice10.Visible = False
        '
        'mnuMain
        '
        Me.mnuMain.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuFile, Me.mnuDevice, Me.mnuChannel, Me.mnuBaseNote})
        '
        'mnuFile
        '
        Me.mnuFile.Index = 0
        Me.mnuFile.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuOpen})
        Me.mnuFile.Text = "Midi &File"
        '
        'mnuOpen
        '
        Me.mnuOpen.Index = 0
        Me.mnuOpen.Text = "&Open"
        '
        'mnuDevice
        '
        Me.mnuDevice.Index = 1
        Me.mnuDevice.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuDevice0, Me.mnuDevice1, Me.mnuDevice2, Me.mnuDevice3, Me.mnuDevice4, Me.mnuDevice5, Me.mnuDevice6, Me.mnuDevice7, Me.mnuDevice8, Me.mnuDevice9, Me.mnuDevice10})
        Me.mnuDevice.Text = "Midi &Device"
        '
        'mnuChannel
        '
        Me.mnuChannel.Index = 2
        Me.mnuChannel.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuChannel0, Me.mnuChannel1, Me.mnuChannel2, Me.mnuChannel3, Me.mnuChannel4, Me.mnuChannel5, Me.mnuChannel6, Me.mnuChannel7, Me.mnuChannel8, Me.mnuChannel9, Me.mnuChannel10, Me.mnuChannel11, Me.mnuChannel12, Me.mnuChannel13, Me.mnuChannel14, Me.mnuChannel15})
        Me.mnuChannel.Text = "&Channel"
        '
        'mnuBaseNote
        '
        Me.mnuBaseNote.Index = 3
        Me.mnuBaseNote.Text = "&Base Note"
        '
        'lblSeqFast
        '
        Me.lblSeqFast.Location = New System.Drawing.Point(854, 51)
        Me.lblSeqFast.Name = "lblSeqFast"
        Me.lblSeqFast.Size = New System.Drawing.Size(41, 27)
        Me.lblSeqFast.TabIndex = 27
        Me.lblSeqFast.Text = "Fast"
        Me.lblSeqFast.TextAlign = System.Drawing.ContentAlignment.TopRight
        '
        'lblSeqNormal
        '
        Me.lblSeqNormal.Location = New System.Drawing.Point(746, 51)
        Me.lblSeqNormal.Name = "lblSeqNormal"
        Me.lblSeqNormal.Size = New System.Drawing.Size(60, 38)
        Me.lblSeqNormal.TabIndex = 26
        Me.lblSeqNormal.Text = "Normal Speed"
        Me.lblSeqNormal.TextAlign = System.Drawing.ContentAlignment.TopCenter
        '
        'lblSeqSlow
        '
        Me.lblSeqSlow.Location = New System.Drawing.Point(654, 51)
        Me.lblSeqSlow.Name = "lblSeqSlow"
        Me.lblSeqSlow.Size = New System.Drawing.Size(41, 27)
        Me.lblSeqSlow.TabIndex = 25
        Me.lblSeqSlow.Text = "Slow"
        '
        'tbSpeed
        '
        Me.tbSpeed.Location = New System.Drawing.Point(658, 14)
        Me.tbSpeed.Maximum = 9
        Me.tbSpeed.Minimum = -9
        Me.tbSpeed.Name = "tbSpeed"
        Me.tbSpeed.Size = New System.Drawing.Size(237, 56)
        Me.tbSpeed.TabIndex = 24
        '
        'btnRemoveSilence
        '
        Me.btnRemoveSilence.Location = New System.Drawing.Point(378, 14)
        Me.btnRemoveSilence.Name = "btnRemoveSilence"
        Me.btnRemoveSilence.Size = New System.Drawing.Size(114, 29)
        Me.btnRemoveSilence.TabIndex = 23
        Me.btnRemoveSilence.Text = "Remove Silence"
        '
        'btnPlay
        '
        Me.btnPlay.Location = New System.Drawing.Point(257, 14)
        Me.btnPlay.Name = "btnPlay"
        Me.btnPlay.Size = New System.Drawing.Size(114, 29)
        Me.btnPlay.TabIndex = 22
        Me.btnPlay.Text = "Play"
        '
        'btnStop
        '
        Me.btnStop.Enabled = False
        Me.btnStop.Location = New System.Drawing.Point(136, 14)
        Me.btnStop.Name = "btnStop"
        Me.btnStop.Size = New System.Drawing.Size(114, 29)
        Me.btnStop.TabIndex = 21
        Me.btnStop.Text = "Stop"
        '
        'btnRecord
        '
        Me.btnRecord.Location = New System.Drawing.Point(14, 14)
        Me.btnRecord.Name = "btnRecord"
        Me.btnRecord.Size = New System.Drawing.Size(114, 29)
        Me.btnRecord.TabIndex = 20
        Me.btnRecord.Text = "Record"
        '
        'gbxInstrument
        '
        Me.gbxInstrument.Controls.Add(Me.cBoxChordMode)
        Me.gbxInstrument.Controls.Add(Me.Label2)
        Me.gbxInstrument.Controls.Add(Me.tBarStereo)
        Me.gbxInstrument.Controls.Add(Me.lblStereo)
        Me.gbxInstrument.Controls.Add(Me.tbBankMSB)
        Me.gbxInstrument.Controls.Add(Me.tbInstrument)
        Me.gbxInstrument.Controls.Add(Me.lblBankMSB)
        Me.gbxInstrument.Controls.Add(Me.lblInstrument)
        Me.gbxInstrument.Location = New System.Drawing.Point(488, 14)
        Me.gbxInstrument.Name = "gbxInstrument"
        Me.gbxInstrument.Size = New System.Drawing.Size(448, 181)
        Me.gbxInstrument.TabIndex = 22
        Me.gbxInstrument.TabStop = False
        Me.gbxInstrument.Text = "MIDI Instrument"
        '
        'cBoxChordMode
        '
        Me.cBoxChordMode.FormattingEnabled = True
        Me.cBoxChordMode.Items.AddRange(New Object() {"single note", "major", "minor", "dominant", "augmented 7th chord"})
        Me.cBoxChordMode.Location = New System.Drawing.Point(215, 147)
        Me.cBoxChordMode.Name = "cBoxChordMode"
        Me.cBoxChordMode.Size = New System.Drawing.Size(156, 24)
        Me.cBoxChordMode.TabIndex = 7
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(111, 147)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(85, 17)
        Me.Label2.TabIndex = 6
        Me.Label2.Text = "Chord mode"
        '
        'tBarStereo
        '
        Me.tBarStereo.LargeChange = 10
        Me.tBarStereo.Location = New System.Drawing.Point(90, 108)
        Me.tBarStereo.Maximum = 127
        Me.tBarStereo.Name = "tBarStereo"
        Me.tBarStereo.Size = New System.Drawing.Size(348, 56)
        Me.tBarStereo.TabIndex = 5
        Me.tBarStereo.TickFrequency = 64
        Me.tBarStereo.Value = 64
        '
        'lblStereo
        '
        Me.lblStereo.AutoSize = True
        Me.lblStereo.Location = New System.Drawing.Point(23, 113)
        Me.lblStereo.Name = "lblStereo"
        Me.lblStereo.Size = New System.Drawing.Size(50, 17)
        Me.lblStereo.TabIndex = 4
        Me.lblStereo.Text = "Stereo"
        '
        'tbBankMSB
        '
        Me.tbBankMSB.LargeChange = 2
        Me.tbBankMSB.Location = New System.Drawing.Point(90, 65)
        Me.tbBankMSB.Maximum = 8
        Me.tbBankMSB.Name = "tbBankMSB"
        Me.tbBankMSB.Size = New System.Drawing.Size(348, 56)
        Me.tbBankMSB.TabIndex = 3
        '
        'tbInstrument
        '
        Me.tbInstrument.Location = New System.Drawing.Point(90, 21)
        Me.tbInstrument.Maximum = 127
        Me.tbInstrument.Name = "tbInstrument"
        Me.tbInstrument.Size = New System.Drawing.Size(348, 56)
        Me.tbInstrument.TabIndex = 2
        '
        'lblBankMSB
        '
        Me.lblBankMSB.AutoSize = True
        Me.lblBankMSB.Location = New System.Drawing.Point(12, 74)
        Me.lblBankMSB.Name = "lblBankMSB"
        Me.lblBankMSB.Size = New System.Drawing.Size(77, 17)
        Me.lblBankMSB.TabIndex = 1
        Me.lblBankMSB.Text = "Bank MSB:"
        '
        'lblInstrument
        '
        Me.lblInstrument.AutoSize = True
        Me.lblInstrument.Location = New System.Drawing.Point(12, 29)
        Me.lblInstrument.Name = "lblInstrument"
        Me.lblInstrument.Size = New System.Drawing.Size(78, 17)
        Me.lblInstrument.TabIndex = 0
        Me.lblInstrument.Text = "Instrument:"
        '
        'tmrSequencer
        '
        '
        'tclMidiFunction
        '
        Me.tclMidiFunction.Controls.Add(Me.tabSequencer)
        Me.tclMidiFunction.Controls.Add(Me.tabWhiteboard)
        Me.tclMidiFunction.Controls.Add(Me.tabDrumMachine)
        Me.tclMidiFunction.Location = New System.Drawing.Point(14, 201)
        Me.tclMidiFunction.Name = "tclMidiFunction"
        Me.tclMidiFunction.SelectedIndex = 0
        Me.tclMidiFunction.Size = New System.Drawing.Size(922, 398)
        Me.tclMidiFunction.TabIndex = 23
        '
        'tabSequencer
        '
        Me.tabSequencer.Controls.Add(Me.btnAppend)
        Me.tabSequencer.Controls.Add(Me.Label5)
        Me.tabSequencer.Controls.Add(Me.Label4)
        Me.tabSequencer.Controls.Add(Me.Label3)
        Me.tabSequencer.Controls.Add(Me.Label1)
        Me.tabSequencer.Controls.Add(Me.tbTranspose)
        Me.tabSequencer.Controls.Add(Me.btnLoop)
        Me.tabSequencer.Controls.Add(Me.lblSeqFast)
        Me.tabSequencer.Controls.Add(Me.btnRecord)
        Me.tabSequencer.Controls.Add(Me.lblSeqNormal)
        Me.tabSequencer.Controls.Add(Me.btnStop)
        Me.tabSequencer.Controls.Add(Me.lblSeqSlow)
        Me.tabSequencer.Controls.Add(Me.btnPlay)
        Me.tabSequencer.Controls.Add(Me.tbSpeed)
        Me.tabSequencer.Controls.Add(Me.btnRemoveSilence)
        Me.tabSequencer.Location = New System.Drawing.Point(4, 25)
        Me.tabSequencer.Name = "tabSequencer"
        Me.tabSequencer.Padding = New System.Windows.Forms.Padding(3)
        Me.tabSequencer.Size = New System.Drawing.Size(914, 369)
        Me.tabSequencer.TabIndex = 0
        Me.tabSequencer.Text = "MIDI Sequencer"
        Me.tabSequencer.UseVisualStyleBackColor = True
        '
        'btnAppend
        '
        Me.btnAppend.Location = New System.Drawing.Point(14, 54)
        Me.btnAppend.Name = "btnAppend"
        Me.btnAppend.Size = New System.Drawing.Size(114, 35)
        Me.btnAppend.TabIndex = 31
        Me.btnAppend.Text = "Append"
        Me.btnAppend.UseVisualStyleBackColor = True
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(482, 139)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(165, 17)
        Me.Label5.TabIndex = 30
        Me.Label5.Text = "playback transpose pitch"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(655, 181)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(29, 17)
        Me.Label4.TabIndex = 30
        Me.Label4.Text = "-60"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(863, 181)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(32, 17)
        Me.Label3.TabIndex = 30
        Me.Label3.Text = "+60"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(769, 181)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(16, 17)
        Me.Label1.TabIndex = 30
        Me.Label1.Text = "0"
        '
        'tbTranspose
        '
        Me.tbTranspose.Location = New System.Drawing.Point(657, 118)
        Me.tbTranspose.Maximum = 127
        Me.tbTranspose.Name = "tbTranspose"
        Me.tbTranspose.Size = New System.Drawing.Size(238, 56)
        Me.tbTranspose.TabIndex = 29
        Me.tbTranspose.TickFrequency = 12
        Me.tbTranspose.Value = 64
        '
        'btnLoop
        '
        Me.btnLoop.Location = New System.Drawing.Point(257, 54)
        Me.btnLoop.Name = "btnLoop"
        Me.btnLoop.Size = New System.Drawing.Size(114, 35)
        Me.btnLoop.TabIndex = 28
        Me.btnLoop.Text = "Enable loop"
        Me.btnLoop.UseVisualStyleBackColor = True
        '
        'tabWhiteboard
        '
        Me.tabWhiteboard.Controls.Add(Me.gbxXAxis)
        Me.tabWhiteboard.Controls.Add(Me.picWhiteboard)
        Me.tabWhiteboard.Controls.Add(Me.gbxYAxis)
        Me.tabWhiteboard.Location = New System.Drawing.Point(4, 25)
        Me.tabWhiteboard.Name = "tabWhiteboard"
        Me.tabWhiteboard.Size = New System.Drawing.Size(914, 369)
        Me.tabWhiteboard.TabIndex = 2
        Me.tabWhiteboard.Text = "MIDI Whiteboard"
        Me.tabWhiteboard.UseVisualStyleBackColor = True
        '
        'gbxXAxis
        '
        Me.gbxXAxis.Controls.Add(Me.lblXValue)
        Me.gbxXAxis.Controls.Add(Me.cboXTitle)
        Me.gbxXAxis.Controls.Add(Me.lblXTitle)
        Me.gbxXAxis.Controls.Add(Me.lblXCaption)
        Me.gbxXAxis.Location = New System.Drawing.Point(389, 14)
        Me.gbxXAxis.Name = "gbxXAxis"
        Me.gbxXAxis.Size = New System.Drawing.Size(174, 92)
        Me.gbxXAxis.TabIndex = 40
        Me.gbxXAxis.TabStop = False
        Me.gbxXAxis.Text = "X - axis"
        '
        'lblXValue
        '
        Me.lblXValue.Location = New System.Drawing.Point(58, 28)
        Me.lblXValue.Name = "lblXValue"
        Me.lblXValue.Size = New System.Drawing.Size(57, 18)
        Me.lblXValue.TabIndex = 38
        '
        'cboXTitle
        '
        Me.cboXTitle.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cboXTitle.Items.AddRange(New Object() {"(none)", "instrument", "velocity", "pitch", "stereo position", "pitch bend"})
        Me.cboXTitle.Location = New System.Drawing.Point(48, 56)
        Me.cboXTitle.Name = "cboXTitle"
        Me.cboXTitle.Size = New System.Drawing.Size(115, 24)
        Me.cboXTitle.TabIndex = 37
        '
        'lblXTitle
        '
        Me.lblXTitle.Location = New System.Drawing.Point(10, 59)
        Me.lblXTitle.Name = "lblXTitle"
        Me.lblXTitle.Size = New System.Drawing.Size(38, 18)
        Me.lblXTitle.TabIndex = 36
        Me.lblXTitle.Text = "Title:"
        '
        'lblXCaption
        '
        Me.lblXCaption.Location = New System.Drawing.Point(10, 28)
        Me.lblXCaption.Name = "lblXCaption"
        Me.lblXCaption.Size = New System.Drawing.Size(48, 18)
        Me.lblXCaption.TabIndex = 0
        Me.lblXCaption.Text = "Value:"
        '
        'picWhiteboard
        '
        Me.picWhiteboard.BackColor = System.Drawing.Color.White
        Me.picWhiteboard.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.picWhiteboard.Location = New System.Drawing.Point(14, 14)
        Me.picWhiteboard.Name = "picWhiteboard"
        Me.picWhiteboard.Size = New System.Drawing.Size(360, 346)
        Me.picWhiteboard.TabIndex = 39
        Me.picWhiteboard.TabStop = False
        '
        'gbxYAxis
        '
        Me.gbxYAxis.Controls.Add(Me.cboYTitle)
        Me.gbxYAxis.Controls.Add(Me.lblYTitle)
        Me.gbxYAxis.Controls.Add(Me.lblYCaption)
        Me.gbxYAxis.Controls.Add(Me.lblYValue)
        Me.gbxYAxis.Location = New System.Drawing.Point(389, 120)
        Me.gbxYAxis.Name = "gbxYAxis"
        Me.gbxYAxis.Size = New System.Drawing.Size(174, 92)
        Me.gbxYAxis.TabIndex = 41
        Me.gbxYAxis.TabStop = False
        Me.gbxYAxis.Text = "Y - axis"
        '
        'cboYTitle
        '
        Me.cboYTitle.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cboYTitle.Items.AddRange(New Object() {"(none)", "instrument", "velocity", "pitch", "stereo position", "pitch bend"})
        Me.cboYTitle.Location = New System.Drawing.Point(48, 56)
        Me.cboYTitle.Name = "cboYTitle"
        Me.cboYTitle.Size = New System.Drawing.Size(115, 24)
        Me.cboYTitle.TabIndex = 37
        '
        'lblYTitle
        '
        Me.lblYTitle.Location = New System.Drawing.Point(10, 59)
        Me.lblYTitle.Name = "lblYTitle"
        Me.lblYTitle.Size = New System.Drawing.Size(38, 18)
        Me.lblYTitle.TabIndex = 36
        Me.lblYTitle.Text = "Title:"
        '
        'lblYCaption
        '
        Me.lblYCaption.Location = New System.Drawing.Point(10, 28)
        Me.lblYCaption.Name = "lblYCaption"
        Me.lblYCaption.Size = New System.Drawing.Size(48, 18)
        Me.lblYCaption.TabIndex = 0
        Me.lblYCaption.Text = "Value:"
        '
        'lblYValue
        '
        Me.lblYValue.Location = New System.Drawing.Point(58, 28)
        Me.lblYValue.Name = "lblYValue"
        Me.lblYValue.Size = New System.Drawing.Size(57, 18)
        Me.lblYValue.TabIndex = 39
        '
        'tabDrumMachine
        '
        Me.tabDrumMachine.Controls.Add(Me.btnLoad)
        Me.tabDrumMachine.Controls.Add(Me.Button1)
        Me.tabDrumMachine.Controls.Add(Me.btnReverse)
        Me.tabDrumMachine.Controls.Add(Me.btnInverse)
        Me.tabDrumMachine.Controls.Add(Me.Label6)
        Me.tabDrumMachine.Controls.Add(Me.tbDrumSpeed)
        Me.tabDrumMachine.Controls.Add(Me.btnRandomBox)
        Me.tabDrumMachine.Controls.Add(Me.BtnRandomDrum)
        Me.tabDrumMachine.Controls.Add(Me.btnRemoveRow)
        Me.tabDrumMachine.Controls.Add(Me.btnRemoveCol)
        Me.tabDrumMachine.Controls.Add(Me.btnAddCol)
        Me.tabDrumMachine.Controls.Add(Me.btnAddRow)
        Me.tabDrumMachine.Controls.Add(Me.btnDrumStop)
        Me.tabDrumMachine.Controls.Add(Me.btnDrumStart)
        Me.tabDrumMachine.Controls.Add(Me.picDrum)
        Me.tabDrumMachine.Location = New System.Drawing.Point(4, 25)
        Me.tabDrumMachine.Name = "tabDrumMachine"
        Me.tabDrumMachine.Padding = New System.Windows.Forms.Padding(3)
        Me.tabDrumMachine.Size = New System.Drawing.Size(914, 369)
        Me.tabDrumMachine.TabIndex = 1
        Me.tabDrumMachine.Text = "Drum Machine"
        Me.tabDrumMachine.UseVisualStyleBackColor = True
        '
        'btnLoad
        '
        Me.btnLoad.Location = New System.Drawing.Point(738, 302)
        Me.btnLoad.Name = "btnLoad"
        Me.btnLoad.Size = New System.Drawing.Size(131, 51)
        Me.btnLoad.TabIndex = 35
        Me.btnLoad.Text = "Load file (.txt)"
        Me.btnLoad.UseVisualStyleBackColor = True
        '
        'Button1
        '
        Me.Button1.Location = New System.Drawing.Point(444, 284)
        Me.Button1.Name = "Button1"
        Me.Button1.Size = New System.Drawing.Size(79, 82)
        Me.Button1.TabIndex = 34
        Me.Button1.Text = "Reset (ONLY slots)"
        Me.Button1.UseVisualStyleBackColor = True
        '
        'btnReverse
        '
        Me.btnReverse.Location = New System.Drawing.Point(100, 325)
        Me.btnReverse.Name = "btnReverse"
        Me.btnReverse.Size = New System.Drawing.Size(78, 38)
        Me.btnReverse.TabIndex = 33
        Me.btnReverse.Text = "Reverse"
        Me.btnReverse.UseVisualStyleBackColor = True
        '
        'btnInverse
        '
        Me.btnInverse.Location = New System.Drawing.Point(100, 284)
        Me.btnInverse.Name = "btnInverse"
        Me.btnInverse.Size = New System.Drawing.Size(78, 35)
        Me.btnInverse.TabIndex = 33
        Me.btnInverse.Text = "Inverse"
        Me.btnInverse.UseVisualStyleBackColor = True
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(292, 349)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(49, 17)
        Me.Label6.TabIndex = 32
        Me.Label6.Text = "Speed"
        '
        'tbDrumSpeed
        '
        Me.tbDrumSpeed.Location = New System.Drawing.Point(206, 317)
        Me.tbDrumSpeed.Maximum = 7
        Me.tbDrumSpeed.Minimum = -7
        Me.tbDrumSpeed.Name = "tbDrumSpeed"
        Me.tbDrumSpeed.Size = New System.Drawing.Size(221, 56)
        Me.tbDrumSpeed.TabIndex = 31
        '
        'btnRandomBox
        '
        Me.btnRandomBox.Location = New System.Drawing.Point(6, 328)
        Me.btnRandomBox.Name = "btnRandomBox"
        Me.btnRandomBox.Size = New System.Drawing.Size(87, 38)
        Me.btnRandomBox.TabIndex = 30
        Me.btnRandomBox.Text = "Rnd box"
        Me.btnRandomBox.UseVisualStyleBackColor = True
        '
        'BtnRandomDrum
        '
        Me.BtnRandomDrum.Location = New System.Drawing.Point(6, 278)
        Me.BtnRandomDrum.Name = "BtnRandomDrum"
        Me.BtnRandomDrum.Size = New System.Drawing.Size(87, 47)
        Me.BtnRandomDrum.TabIndex = 30
        Me.BtnRandomDrum.Text = "Random Instrument"
        Me.BtnRandomDrum.UseVisualStyleBackColor = True
        '
        'btnRemoveRow
        '
        Me.btnRemoveRow.Location = New System.Drawing.Point(529, 328)
        Me.btnRemoveRow.Name = "btnRemoveRow"
        Me.btnRemoveRow.Size = New System.Drawing.Size(63, 41)
        Me.btnRemoveRow.TabIndex = 29
        Me.btnRemoveRow.Text = "- row"
        Me.btnRemoveRow.UseVisualStyleBackColor = True
        '
        'btnRemoveCol
        '
        Me.btnRemoveCol.Location = New System.Drawing.Point(607, 328)
        Me.btnRemoveCol.Name = "btnRemoveCol"
        Me.btnRemoveCol.Size = New System.Drawing.Size(69, 41)
        Me.btnRemoveCol.TabIndex = 28
        Me.btnRemoveCol.Text = "- col"
        Me.btnRemoveCol.UseVisualStyleBackColor = True
        '
        'btnAddCol
        '
        Me.btnAddCol.Location = New System.Drawing.Point(607, 284)
        Me.btnAddCol.Name = "btnAddCol"
        Me.btnAddCol.Size = New System.Drawing.Size(69, 41)
        Me.btnAddCol.TabIndex = 28
        Me.btnAddCol.Text = "+ col"
        Me.btnAddCol.UseVisualStyleBackColor = True
        '
        'btnAddRow
        '
        Me.btnAddRow.Location = New System.Drawing.Point(529, 284)
        Me.btnAddRow.Name = "btnAddRow"
        Me.btnAddRow.Size = New System.Drawing.Size(63, 41)
        Me.btnAddRow.TabIndex = 28
        Me.btnAddRow.Text = "+ row"
        Me.btnAddRow.UseVisualStyleBackColor = True
        '
        'btnDrumStop
        '
        Me.btnDrumStop.Location = New System.Drawing.Point(324, 284)
        Me.btnDrumStop.Name = "btnDrumStop"
        Me.btnDrumStop.Size = New System.Drawing.Size(114, 29)
        Me.btnDrumStop.TabIndex = 27
        Me.btnDrumStop.Text = "Stop Drum"
        '
        'btnDrumStart
        '
        Me.btnDrumStart.Location = New System.Drawing.Point(193, 284)
        Me.btnDrumStart.Name = "btnDrumStart"
        Me.btnDrumStart.Size = New System.Drawing.Size(114, 29)
        Me.btnDrumStart.TabIndex = 26
        Me.btnDrumStart.Text = "Start Drum"
        '
        'picDrum
        '
        Me.picDrum.BackColor = System.Drawing.SystemColors.Window
        Me.picDrum.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle
        Me.picDrum.ForeColor = System.Drawing.SystemColors.WindowText
        Me.picDrum.Location = New System.Drawing.Point(129, 18)
        Me.picDrum.Name = "picDrum"
        Me.picDrum.Size = New System.Drawing.Size(360, 53)
        Me.picDrum.TabIndex = 25
        Me.picDrum.TabStop = False
        '
        'tmrDrumPlayback
        '
        Me.tmrDrumPlayback.Interval = 250
        '
        'txtStatus
        '
        Me.txtStatus.AutoSize = True
        Me.txtStatus.Location = New System.Drawing.Point(22, 606)
        Me.txtStatus.Name = "txtStatus"
        Me.txtStatus.Size = New System.Drawing.Size(107, 17)
        Me.txtStatus.TabIndex = 24
        Me.txtStatus.Text = "status message"
        '
        'txtMidiSignal
        '
        Me.txtMidiSignal.AutoSize = True
        Me.txtMidiSignal.Location = New System.Drawing.Point(23, 636)
        Me.txtMidiSignal.Name = "txtMidiSignal"
        Me.txtMidiSignal.Size = New System.Drawing.Size(74, 17)
        Me.txtMidiSignal.TabIndex = 25
        Me.txtMidiSignal.Text = "midi signal"
        '
        'btnReset
        '
        Me.btnReset.Location = New System.Drawing.Point(942, 68)
        Me.btnReset.Name = "btnReset"
        Me.btnReset.Size = New System.Drawing.Size(55, 90)
        Me.btnReset.TabIndex = 26
        Me.btnReset.Text = "Reset MIDI"
        Me.btnReset.UseVisualStyleBackColor = True
        '
        'frmMidiPiano
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(1023, 662)
        Me.Controls.Add(Me.btnReset)
        Me.Controls.Add(Me.txtMidiSignal)
        Me.Controls.Add(Me.txtStatus)
        Me.Controls.Add(Me.tclMidiFunction)
        Me.Controls.Add(Me.gbxInstrument)
        Me.Controls.Add(Me.vsbVolume)
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
        Me.Controls.Add(Me.lblVolume)
        Me.KeyPreview = True
        Me.Location = New System.Drawing.Point(11, 49)
        Me.Menu = Me.mnuMain
        Me.Name = "frmMidiPiano"
        Me.Text = "VB Midi Piano"
        CType(Me.tbSpeed, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbxInstrument.ResumeLayout(False)
        Me.gbxInstrument.PerformLayout()
        CType(Me.tBarStereo, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbBankMSB, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbInstrument, System.ComponentModel.ISupportInitialize).EndInit()
        Me.tclMidiFunction.ResumeLayout(False)
        Me.tabSequencer.ResumeLayout(False)
        Me.tabSequencer.PerformLayout()
        CType(Me.tbTranspose, System.ComponentModel.ISupportInitialize).EndInit()
        Me.tabWhiteboard.ResumeLayout(False)
        Me.gbxXAxis.ResumeLayout(False)
        CType(Me.picWhiteboard, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbxYAxis.ResumeLayout(False)
        Me.tabDrumMachine.ResumeLayout(False)
        Me.tabDrumMachine.PerformLayout()
        CType(Me.tbDrumSpeed, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.picDrum, System.ComponentModel.ISupportInitialize).EndInit()
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
    Dim chordMode As Integer
    Dim speed As Double = 1
    Dim drumSpeed As Double = 1
    Dim drumPlaybackDefaultInterval As Double = 1
    Dim loopMode As Boolean
    Dim playBackTranspose As Integer

    Dim key As CheckBoxArray ' an array of check box for keys
    Dim chan As MenuItemArray ' an array of menu item for channel
    Dim device As MenuItemArray ' an array of menu item for midi device

    ' channel data
    Dim midiChannels(16) As midiChannel

    ' for recording
    Dim isRecording As Boolean ' recording status
    Dim startTime As System.DateTime ' the time of starting recording
    Shared midiSequence As SequenceData ' store MIDI sequence
    Dim currentIndex As Integer ' store the current playing sequence index

    ' for MIDI whiteboard
    Dim lastMidiMessage As Integer = -1 ' previous MIDI message sent to the card
    Dim cbXselected As Short = 0 ' selection of the X axis
    Dim cbYselected As Short = 3 ' selection of the Y axis

    ' for drum machine
    Dim DRUM_INSTRUMENT As Short = 2 ' CURRENT number of drum instruments
    Dim DRUM_SLOT As Short = 8 ' CURRENT number of slots across the drum machine
    Dim drumSlot(9, 50) As Boolean ' Slot On/Off for the drum machine
    Dim drumNumber(9) As Short ' The instrument for the drums
    Dim drumMessageSent(9) As Boolean ' True if a note-on midi message for a drum is sent

    ' drum machine continued
    Dim comboBoxDrums(9) As System.Windows.Forms.ComboBox

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

        ' initialize channel menuitemarray (not generated by vb)
        chan = New MenuItemArray
        AddHandler mnuChannel0.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel0)

        AddHandler mnuChannel1.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel1)

        AddHandler mnuChannel2.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel2)

        AddHandler mnuChannel3.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel3)

        AddHandler mnuChannel4.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel4)

        AddHandler mnuChannel5.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel5)

        AddHandler mnuChannel6.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel6)

        AddHandler mnuChannel7.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel7)

        AddHandler mnuChannel8.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel8)

        AddHandler mnuChannel9.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel9)

        AddHandler mnuChannel10.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel10)

        AddHandler mnuChannel11.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel11)

        AddHandler mnuChannel12.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel12)

        AddHandler mnuChannel13.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel13)

        AddHandler mnuChannel14.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel14)

        AddHandler mnuChannel15.Click, AddressOf chan_Click
        chan.AddNewMenuItem(mnuChannel15)

        ' initialize device menuitemarray (not generated by vb)
        device = New MenuItemArray

        AddHandler mnuDevice0.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice0)

        AddHandler mnuDevice1.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice1)

        AddHandler mnuDevice2.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice2)

        AddHandler mnuDevice3.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice3)

        AddHandler mnuDevice4.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice4)

        AddHandler mnuDevice5.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice5)

        AddHandler mnuDevice6.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice6)

        AddHandler mnuDevice7.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice7)

        AddHandler mnuDevice8.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice8)

        AddHandler mnuDevice9.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice9)

        AddHandler mnuDevice10.Click, AddressOf device_Click
        device.AddNewMenuItem(mnuDevice10)
    End Sub
#End Region
    Public Sub generateMidiSignal(ByVal signal As Integer)
        midiOutShortMsg(hmidi, signal)

        ' 2.1 Start recording a MIDI sequence
        ' Store the message
        If isRecording Then
            midiSequence.AddSequenceData(signal, DateTime.Now.Subtract(startTime))
            txtStatus.Text = "Recorded " + midiSequence.dataLength.ToString() + " midi signals so far"
        End If


        Dim i As Long
        Dim bin As String
        If signal < 0 Then bin = bin + "1" Else bin = bin + "0"
        For i = 23 To 0 Step -1
            If signal And (2 ^ i) Then   ' Use the logical "AND" operator.
                bin = bin + "1"
            Else
                bin = bin + "0"
            End If
            If (i Mod 8 = 0) Then
                bin += " "
            End If
        Next
        txtMidiSignal.Text = bin + " (byte3 byte2 byte1)"
    End Sub

    Public Sub generateMidiSignal(ByVal byte1 As Integer, ByVal byte2 As Integer, ByVal byte3 As Integer)
        midimsg = byte1 + byte2 * &H100 + byte3 * &H10000
        generateMidiSignal(midimsg)
    End Sub
    ' Set the value for the starting note of the piano
    Public Sub base_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuBaseNote.Click
        Dim s As String
        Dim i As Short
        s = InputBox("Enter the new base note for the keyboard (0 - 111)", "Base note", CStr(baseNote))
        If IsNumeric(s) Then
            i = CShort(s)
            If i >= 0 And i < 112 Then
                baseNote = i
            End If
        End If
    End Sub

    ' Select the midi output channel
    Public Sub chan_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Dim index As Integer
        index = CType(sender, System.Windows.Forms.MenuItem).Index
        chan(channel).Checked = False
        channel = index
        chan(channel).Checked = True
        tbInstrument.Value = midiChannels(channel).instrument
        tbBankMSB.Value = midiChannels(channel).bankMSB
        tBarStereo.Value = midiChannels(channel).panning
    End Sub

    ' Open the midi device selected in the menu. The menu index equals the midi device number + 1.
    Public Sub device_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Dim index As Integer
        index = CType(sender, System.Windows.Forms.MenuItem).Index
        device(curDevice + 1).Checked = False
        device(index).Checked = True
        curDevice = index - 1
        rc = midiOutClose(hmidi)
        rc = midiOutOpen(hmidi, curDevice, 0, 0, 0)
        If rc <> 0 Then
            MessageBox.Show("Couldn't open midi out, rc = " & rc, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If
    End Sub

    ' If user presses a keyboard key, start the corresponding midi note
    Private Sub frmMidiPiano_KeyDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyDown
        Dim KeyCode As Short = e.KeyCode

        StartNote(NoteFromKey(KeyCode))
    End Sub

    ' If user lifts a keyboard key, stop the corresponding midi note
    Private Sub frmMidiPiano_KeyUp(ByVal sender As System.Object, ByVal e As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyUp
        Dim KeyCode As Short = e.KeyCode

        StopNote(NoteFromKey(KeyCode))
    End Sub

    Private Sub frmMidiPiano_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        Dim i As Integer
        Dim caps As MIDIOUTCAPS

        Try
            initControlArray()

            ' set initial state
            cBoxChordMode.SelectedIndex = 0
            loopMode = False
            playBackTranspose = 0

            ' set channels
            For i = 0 To 15
                midiChannels(i) = New midiChannel(i)
            Next


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
            vsbVolume.Value = vsbVolume.Maximum - volume

            midiSequence = Nothing
            isRecording = False

            cboXTitle.SelectedIndex = cbXselected
            cboYTitle.SelectedIndex = cbYselected

            drumNumber(1) = &H3C
            drumNumber(2) = &H3D

            midiSequence = Nothing
            isRecording = False

            btnStop.Enabled = False

            'set drum related
            addComboBoxDrum(1)
            addComboBoxDrum(2)
            comboBoxDrums(1).SelectedIndex = drumNumber(1) - 35
            comboBoxDrums(2).SelectedIndex = drumNumber(2) - 35
            drumPlaybackDefaultInterval = tmrDrumPlayback.Interval


        Catch ex As Exception

        End Try
    End Sub

    Private Sub frmMidiPiano_Closed(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Closed
        ' Close current midi device
        rc = midiOutClose(hmidi)
    End Sub

    ' Start a note when user click on it
    Public Sub key_MouseDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs)
        Dim index As Integer
        index = CType(sender, System.Windows.Forms.CheckBox).Tag
        StartNote(index)
    End Sub

    ' Stop the note when user lifts the mouse button
    Public Sub key_MouseUp(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs)
        Dim index As Integer
        index = CType(sender, System.Windows.Forms.CheckBox).Tag
        StopNote(index)
    End Sub

    ' Press the button and send midi start event
    Private Sub StartNote(ByRef Index As Short)
        If Index = INVALID_NOTE Then
            Exit Sub
        End If
        If key(Index).CheckState = 1 Then
            Exit Sub
        End If

        Dim chord1, chord2, chord3 As Integer
        If chordMode = 1 Then
            chord1 = 4
            chord2 = 7
        ElseIf chordMode = 2 Then
            chord1 = 3
            chord2 = 7
        ElseIf chordMode = 3 Then
            chord1 = 4
            chord2 = 7
            chord3 = 10
        ElseIf chordMode = 4 Then
            chord1 = 4
            chord2 = 8
            chord3 = 11
        End If



        key(Index).CheckState = System.Windows.Forms.CheckState.Checked
        midimsg = &H90 + ((baseNote + Index) * &H100) + (volume * &H10000) + channel
        generateMidiSignal(midimsg)
        If (chordMode >= 1) Then
            midimsg = &H90 + ((baseNote + Index + chord1) * &H100) + (volume * &H10000) + channel
            generateMidiSignal(midimsg)
            midimsg = &H90 + ((baseNote + Index + chord2) * &H100) + (volume * &H10000) + channel
            generateMidiSignal(midimsg)
        End If
        If (chordMode >= 3) Then
            midimsg = &H90 + ((baseNote + Index + chord3) * &H100) + (volume * &H10000) + channel
            generateMidiSignal(midimsg)
        End If
    End Sub

    ' Raise the button and send midi stop event
    Private Sub StopNote(ByRef Index As Short)
        If Index = INVALID_NOTE Then
            Exit Sub
        End If

        Dim chord1, chord2, chord3 As Integer
        If chordMode = 1 Then
            chord1 = 4
            chord2 = 7
        ElseIf chordMode = 2 Then
            chord1 = 3
            chord2 = 7
        ElseIf chordMode = 3 Then
            chord1 = 4
            chord2 = 7
            chord3 = 10
        ElseIf chordMode = 4 Then
            chord1 = 4
            chord2 = 8
            chord3 = 11
        End If

        key(Index).CheckState = System.Windows.Forms.CheckState.Unchecked
        midimsg = &H80 + ((baseNote + Index) * &H100) + (volume * &H10000) + channel
        generateMidiSignal(midimsg)
        If (chordMode >= 1) Then
            midimsg = &H80 + ((baseNote + Index + chord1) * &H100) + (volume * &H10000) + channel
            generateMidiSignal(midimsg)
            midimsg = &H80 + ((baseNote + Index + chord2) * &H100) + (volume * &H10000) + channel
            generateMidiSignal(midimsg)
        End If
        If (chordMode >= 3) Then
            midimsg = &H80 + ((baseNote + Index + chord3) * &H100) + (volume * &H10000) + channel
            generateMidiSignal(midimsg)
        End If
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
    Private Sub vsbVolume_Scroll(ByVal sender As System.Object, ByVal e As System.Windows.Forms.ScrollEventArgs) Handles vsbVolume.Scroll
        Select Case e.Type
            Case System.Windows.Forms.ScrollEventType.EndScroll
                volume = vsbVolume.Maximum - e.NewValue
        End Select
    End Sub

    ' Start recording MIDI sequence
    Private Sub btnRecord_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnRecord.Click
        ' 2.1 Start recording a MIDI sequence
        ' Initialize the sequence data
        isRecording = True
        startTime = DateTime.Now
        midiSequence = Nothing
        midiSequence = New SequenceData
        ' Send the messages for the instrument so that the playback will match
        midiChannels(channel).changeInstrument(midiChannels(channel).instrument)
        midiChannels(channel).changePanning(midiChannels(channel).panning)
        midiChannels(channel).changeBankMSB(midiChannels(channel).bankMSB)



        txtStatus.Text = "Start recording.."
        btnRecord.Enabled = False
        btnPlay.Enabled = False
        btnStop.Enabled = True
    End Sub

    ' Start appending MIDI sequence
    Private Sub btnAppend_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnAppend.Click
        If midiSequence Is Nothing Then
            btnRecord_Click(sender, e)
        End If
        isRecording = True
        

        startTime = DateTime.Now.Subtract(midiSequence.data(midiSequence.dataLength - 1).time)
        ' Send the messages for the instrument so that the playback will match
        generateMidiSignal(&HC0 + channel, midiChannels(channel).instrument, 0)

        txtStatus.Text = "Start appending.."
        btnRecord.Enabled = False
        btnPlay.Enabled = False
        btnStop.Enabled = True
    End Sub

    ' Stop recording MIDI sequence
    Private Sub btnStop_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnStop.Click
        ' 2.2 Stop recording a MIDI sequence
        ' Stop the recording
        Dim i As Integer
        For i = 0 To 15
            generateMidiSignal(&HB0 + i + (&H7B) * &H100)
        Next
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
    Private Sub btnPlay_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnPlay.Click
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

            ' 2.whatever : Transpose pitch
            ' for all notes on message, transpose pitch
            midimsg = midiSequence.data(currentIndex).midiMsg
            Dim statusBitSeq As Integer
            statusBitSeq = (midiSequence.data(currentIndex).midiMsg) >> 4 And &HF

            ' retrieve pitch value and add by playbackTranspose value
            If (statusBitSeq = &H9) Or (statusBitSeq = &H8) Then
                Dim pitchValue As Integer = (midimsg >> 8) And &H7F
                Dim pitchDiff As Integer
                pitchDiff = playBackTranspose

                pitchValue += playBackTranspose

                If pitchValue > 127 Then
                    pitchDiff = 127 - pitchValue + playBackTranspose
                ElseIf pitchValue < 0 Then
                    pitchDiff = -pitchValue + playBackTranspose
                End If

                ' add pitchDiff to midimsg
                'txtStatus.Text = (pitchValue - playBackTranspose + pitchDiff)
                pitchDiff = pitchDiff << 8
                midimsg += pitchDiff

            End If

            generateMidiSignal(midimsg)
            currentIndex += 1
            If currentIndex = midiSequence.dataLength Then
                Exit While
            End If
        End While

        If currentIndex < midiSequence.dataLength Then
            tmrSequencer.Interval = CInt((midiSequence.data(currentIndex).time.TotalMilliseconds - midiSequence.data(currentIndex - 1).time.TotalMilliseconds) * speed) + 1
            'txtStatus.Text = "interval set to " + tmrSequencer.Interval.ToString() + " ms"
            tmrSequencer.Enabled = True
            'txtStatus.Text = "more " + currentIndex.ToString()
        ElseIf loopMode = True Then
            btnPlay_Click(sender, e)
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
        Dim firstNoteOnSignalIndex As Integer
        Dim firstNoteOnSignalTime As System.TimeSpan
        Dim i As Integer
        For i = 0 To UBound(midiSequence.data)
            If ((midiSequence.data(i).midiMsg) >> 4 And &HF) = &H9 Then
                firstNoteOnSignalIndex = i
                firstNoteOnSignalTime = midiSequence.data(i).time
                Exit For
            End If
        Next
        txtStatus.Text = "remove silence -> start of the note = " + firstNoteOnSignalTime.ToString + " at index " + firstNoteOnSignalIndex.ToString
        For i = 0 To firstNoteOnSignalIndex - 1
            midiSequence.data(i).time = System.TimeSpan.Zero
        Next
        For i = firstNoteOnSignalIndex To UBound(midiSequence.data)
            midiSequence.data(i).time = midiSequence.data(i).time.Subtract(firstNoteOnSignalTime)
        Next
    End Sub


    Private Sub tbInstrument_Scroll(sender As Object, e As EventArgs) Handles tbInstrument.Scroll
        midiChannels(channel).changeInstrument(tbInstrument.Value)
    End Sub

    Private Sub tbBankMSB_Scroll(sender As Object, e As EventArgs) Handles tbBankMSB.Scroll
        midiChannels(channel).changeBankMSB(tbBankMSB.Value)
    End Sub

    Private Sub tBarStereo_Scroll(sender As Object, e As EventArgs) Handles tBarStereo.Scroll

        midiChannels(channel).changePanning(tBarStereo.Value)
    End Sub

    Private Function sendMsgForWhiteboard(ByVal x As Double, ByVal y As Double) As Integer
        Dim pitch As Integer

        ' Instrument is selected
        If cbXselected = 1 Or cbYselected = 1 Then

            ' ***** Add your code here
            tbInstrument.Value = IIf(cbXselected = 1, x * 127, y * 127)
            midiChannels(channel).changeInstrument(tbInstrument.Value)
        End If

        ' Velocity is selected
        If cbXselected = 2 Or cbYselected = 2 Then

            ' ***** Add your code here
            volume = IIf(cbXselected = 2, x * 127, y * 127)
            vsbVolume.Value = 127 - volume
        End If

        ' Stereo positioning is selected
        If cbXselected = 4 Or cbYselected = 4 Then
            tBarStereo.Value = IIf(cbXselected = 4, x * 127, y * 127)
            midiChannels(channel).changePanning(tBarStereo.Value)

        End If

        ' Pitch bend is selected
        If cbXselected = 5 Or cbYselected = 5 Then
            'tBarStereo.Value = IIf(cbXselected = 4, x * 127, y * 127)
            'midiChannels(channel).changePanning(tBarStereo.Value)
            Dim pitchbendTemp As Integer = IIf(cbXselected = 5, x * 16383, y * 16383)
            Dim pitchbendMSB As Integer = pitchbendTemp / 128
            Dim pitchbendLSB As Integer = pitchbendTemp Mod 128
            generateMidiSignal(&HE0 + channel + pitchbendLSB * &H100 + pitchbendMSB * &H10000)
        End If

        ' Pitch is selected
        If cbXselected = 3 Or cbYselected = 3 Then

            ' ***** Add your code here
            pitch = IIf(cbXselected = 3, x * 127, y * 127)
            If lastMidiMessage <> -1 Then
                midimsg = lastMidiMessage And &HFFFFEF
                generateMidiSignal(midimsg)
            End If
            generateMidiSignal(&H90 + channel, pitch, volume)
            midimsg = &H90 + channel + pitch * &H100 + volume * &H10000
            lastMidiMessage = midimsg
        End If


    End Function

    Private Sub picWhiteboard_MouseMove(ByVal sender As Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picWhiteboard.MouseMove

        ' ***** Add your code here
        Dim x, y As Double
        ' Check the range of the x and y values
        ' Send MIDI messages based on the x and y range
        ' Display the values in the x and y labels
        If Not e.Button = MouseButtons.Left Or
            e.X < 0 Or e.X >= picWhiteboard.Width Or
            e.Y < 0 Or e.Y >= picWhiteboard.Height Then
            Exit Sub
        End If

        x = e.X / (picWhiteboard.Width - 1)
        y = e.Y / (picWhiteboard.Height - 1)
        sendMsgForWhiteboard(x, y)
    End Sub

    Private Sub cboXTitle_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cboXTitle.SelectedIndexChanged
        If cboXTitle.SelectedIndex = cbYselected Then
            cboXTitle.SelectedIndex = cbXselected
        Else
            cbXselected = cboXTitle.SelectedIndex
        End If
    End Sub

    Private Sub cboYTitle_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cboYTitle.SelectedIndexChanged
        If cboYTitle.SelectedIndex = cbXselected Then
            cboYTitle.SelectedIndex = cbYselected
        Else
            cbYselected = cboYTitle.SelectedIndex
        End If
    End Sub

    Private Sub picWhiteboard_MouseDown(ByVal sender As Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picWhiteboard.MouseDown

        ' ***** Add your code here
        picWhiteboard_MouseMove(sender, e)
    End Sub

    Private Sub picWhiteboard_MouseUp(ByVal sender As Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picWhiteboard.MouseUp

        ' ***** Add your code here
        If lastMidiMessage <> -1 Then
            midimsg = lastMidiMessage And &HFFFFEF
            generateMidiSignal(midimsg)
            lastMidiMessage = -1
        End If
    End Sub

    ' Draw the drum slots in a PictureBox (picDrum) in two rows
    Private Sub DrawDrumConfiguration(ByVal g As Graphics)
        Dim Slot, Drum As Short
        Dim X1, X2 As Single
        Dim Y1, Y2 As Single
        Dim Width, Height As Single

        ' The width and height of a slot in the drum machine
        Width = picDrum.ClientRectangle.Width / DRUM_SLOT
        Height = picDrum.ClientRectangle.Height / DRUM_INSTRUMENT

        g.Clear(Color.White)

        'Create pens
        Dim blackBrush As New SolidBrush(Color.Black)
        Dim redPen As New Pen(Color.Red, 3)

        For Slot = 1 To DRUM_SLOT
            X1 = Width * (Slot - 1)
            X2 = Width * Slot

            For Drum = 1 To DRUM_INSTRUMENT
                Y1 = Height * (Drum - 1)
                Y2 = Height * Drum

                ' Draw a black box if the slot is selected
                If drumSlot(Drum, Slot) Then
                    g.FillRectangle(blackBrush, X1, Y1, Width, Height)
                End If
            Next
            g.DrawLine(redPen, X1, 0, X1, (picDrum.ClientRectangle.Height))
        Next

        ' Draw the red separators between the slots
        For Drum = 1 To DRUM_INSTRUMENT - 1
            g.DrawLine(redPen, 0, Height * Drum, (picDrum.ClientRectangle.Width), Height * Drum)
        Next
    End Sub

    Private Sub picDrum_MouseDown(ByVal sender As System.Object, ByVal e As System.Windows.Forms.MouseEventArgs) Handles picDrum.MouseDown
        Dim X As Single = e.X
        Dim Y As Single = e.Y
        Dim Drum, Slot As Short
        Dim Width, Height As Single

        Width = picDrum.ClientRectangle.Width / DRUM_SLOT
        Height = picDrum.ClientRectangle.Height / DRUM_INSTRUMENT

        ' Determine the slot where the user has selected
        Slot = Math.Floor(X / Width) + 1
        Drum = Math.Floor(Y / Height) + 1

        ' Set/unset the drum slot
        drumSlot(Drum, Slot) = Not drumSlot(Drum, Slot)

        ' Redraw the drum machine
        DrawDrumConfiguration(picDrum.CreateGraphics())
    End Sub

    Private Sub tmrDrumPlayback_Tick(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles tmrDrumPlayback.Tick
        Static Slot As Short
        Static Slot_p As Short = 0
        Dim Drum As Short
        Dim X1, X2, Width As Single
        Dim bluePen As New Pen(Color.Blue, 3)
        Dim redPen As New Pen(Color.Red, 3)
        Dim g As Graphics = picDrum.CreateGraphics
        tmrDrumPlayback.Interval = drumPlaybackDefaultInterval * drumSpeed

        Width = picDrum.ClientRectangle.Width / DRUM_SLOT

        X1 = Width * (Slot_p - 1)
        X2 = Width * (Slot - 1)


        'Draw the red line to overwrite the blue line

        ' *** Add your code here
        picDrum.CreateGraphics().DrawLine(redPen, X1, 0, X1, (picDrum.ClientRectangle.Height))

        ' Initialize the slot number
        If Slot = 0 Then Slot = 1

        ' Start/Stop a drum for each row in the drum machine
        For Drum = 1 To DRUM_INSTRUMENT
            If drumMessageSent(Drum) Then
                ' You need to stop any drum note already sent to
                ' the midi card by checking the variable DrumMessageSent

                ' *** Add your code here
                generateMidiSignal(&H80 + 9, drumNumber(Drum), volume)
                drumMessageSent(Drum) = False
            End If

            If drumSlot(Drum, Slot) Then
                ' Here, a drum slot is selected that means you have to
                ' start a midi note with the drum sound

                ' *** Add your code here
                generateMidiSignal(&H90 + 9, drumNumber(Drum), volume)

                drumMessageSent(Drum) = True
            Else
                drumMessageSent(Drum) = False
            End If
        Next


        'Draw the blue line

        ' *** Add your code here
        picDrum.CreateGraphics().DrawLine(bluePen, X2, 0, X2, (picDrum.ClientRectangle.Height))

        'Save the current position
        Slot_p = Slot

        ' Increase the number by 1
        Slot = (Slot Mod DRUM_SLOT) + 1
    End Sub

    ' Draw the drum machine
    Private Sub picDrum_Paint(ByVal sender As System.Object, ByVal e As System.Windows.Forms.PaintEventArgs) Handles picDrum.Paint
        DrawDrumConfiguration(e.Graphics())
    End Sub

    Private Sub btnDrumstart_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnDrumStart.Click
        ' start the drum timer
        tmrDrumPlayback.Enabled = True
        tmrDrumPlayback.Interval = drumPlaybackDefaultInterval * drumSpeed
    End Sub

    Private Sub btnDrumstop_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnDrumStop.Click
        Dim Drum As Short

        'ReDraw the drum machine
        DrawDrumConfiguration(picDrum.CreateGraphics())

        ' stop the drum timer
        tmrDrumPlayback.Enabled = False

        ' You need to stop any drum note already sent to the midi card

        ' *** Add your code here
        For Drum = 1 To DRUM_INSTRUMENT
            If drumMessageSent(Drum) Then
                ' You need to stop any drum note already sent to
                ' the midi card by checking the variable DrumMessageSent

                ' *** Add your code here
                generateMidiSignal(&H80 + 9, drumNumber(Drum), volume)
                drumMessageSent(Drum) = False
            End If

        Next
    End Sub

    Private Sub picDisplay_Paint(ByVal sender As Object, ByVal e As System.Windows.Forms.PaintEventArgs)
        Dim g As Graphics = e.Graphics()

        g.Clear(Color.White)

        ' This code simply draws three rectangles on the picture box
        g.FillRectangle(Brushes.Black, 10, 10, 10, 2)
        g.FillRectangle(Brushes.Black, 20, 20, 10, 2)
        g.FillRectangle(Brushes.Black, 30, 30, 10, 2)
    End Sub

    Private Sub mnuOpen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuOpen.Click
        Dim dlg As New OpenFileDialog
        Dim midiFile As MIDIFile
        Dim midiSequences() As SequenceData

        ' Ask for the MIDI file
        dlg.DefaultExt = "mid"
        dlg.Filter = "MIDI files (*.mid)|*.mid"
        dlg.Multiselect = False

        If dlg.ShowDialog() = DialogResult.OK Then
            ' Load the file into the MIDIFile structure
            midiFile = New MIDIFile(dlg.FileName)

            ' Convert the MIDI file into the SequenceData memory structure
            midiSequences = midiFile.ConvertToSequence()
            If midiSequences.Length > 0 Then
                ' Here the first track of the MIDI file is set into the sequencer memory
                midiSequence = midiSequences(0)
            End If
            MsgBox("midi file loaded into midi sequencer. hit play button to play the music")
        End If
    End Sub



    Private Sub cBoxChordMode_SelectedIndexChanged(sender As Object, e As EventArgs) Handles cBoxChordMode.SelectedIndexChanged
        chordMode = cBoxChordMode.SelectedIndex
    End Sub

    Private Sub btnReset_Click(sender As Object, e As EventArgs) Handles btnReset.Click
        Dim i As Integer
        For i = 0 To 15
            midiChannels(i).changeBankMSB(0)
            midiChannels(i).changeInstrument(0)
            midiChannels(i).changePanning(64)
            generateMidiSignal(&HB0 + i + (&H7B) * &H100)
        Next
        tbBankMSB.Value = 0
        tbInstrument.Value = 0
        tBarStereo.Value = 64
        volume = 127
        vsbVolume.Value = 0
    End Sub


    Private Sub btnLoop_Click(sender As Object, e As EventArgs) Handles btnLoop.Click
        If loopMode = False Then
            btnLoop.Text = "Disable loop"
            loopMode = True
        Else
            btnLoop.Text = "Enable loop"
            loopMode = False
        End If
    End Sub

    Private Sub TrackBar1_Scroll(sender As Object, e As EventArgs) Handles tbTranspose.Scroll
        playBackTranspose = tbTranspose.Value - 64
    End Sub

    Private Sub drumAddRow()
        If DRUM_INSTRUMENT = 9 Then
            Exit Sub
        End If
        DRUM_INSTRUMENT += 1
        addComboBoxDrum(DRUM_INSTRUMENT)
        picDrum.Size = New System.Drawing.Size(picDrum.Width, picDrum.Height + 28)
        drumNumber(DRUM_INSTRUMENT) = 60
        comboBoxDrums(DRUM_INSTRUMENT).SelectedIndex = 60 - 35

    End Sub
    Private Sub drumRemoveRow()
        If DRUM_INSTRUMENT = 1 Then
            Exit Sub
        End If
        Me.tabDrumMachine.Controls.Remove(comboBoxDrums(DRUM_INSTRUMENT))
        comboBoxDrums(DRUM_INSTRUMENT) = Nothing
        DRUM_INSTRUMENT -= 1
        picDrum.Size = New System.Drawing.Size(picDrum.Width, picDrum.Height - 28)
    End Sub
    Private Sub drumAddCol()
        If DRUM_SLOT < 16 Then
            DRUM_SLOT += 1
            picDrum.Size = New System.Drawing.Size(picDrum.Width + 44, picDrum.Height)
            DrawDrumConfiguration(picDrum.CreateGraphics)
        Else
            DRUM_SLOT += 1
            DrawDrumConfiguration(picDrum.CreateGraphics)
        End If

    End Sub
    Private Sub drumRemoveCol()
        If DRUM_SLOT = 1 Then
            Exit Sub
        End If
        If DRUM_SLOT <= 16 Then
            DRUM_SLOT -= 1
            picDrum.Size = New System.Drawing.Size(picDrum.Width - 44, picDrum.Height)
            DrawDrumConfiguration(picDrum.CreateGraphics)
        Else
            DRUM_SLOT -= 1
            DrawDrumConfiguration(picDrum.CreateGraphics)
        End If

    End Sub


    Private Sub addComboBoxDrum(index As Integer)
        comboBoxDrums(index) = New System.Windows.Forms.ComboBox()
        Me.tabDrumMachine.Controls.Add(comboBoxDrums(index))
        comboBoxDrums(index).FormattingEnabled = True
        comboBoxDrums(index).Items.AddRange(New Object() {"35 Acoustic Bass Drum", "36 Bass Drum 1", "37 Side Stick", "38 Acoustic Snare", "39 Hand Clap", "40 Electric Snare ", "41 Low Floor Tom", "42 Closed Hi-Hat", "43 High Floor Tom", "44 Pedal Hi-Hat  ", "45 Low Tom ", "46 Open Hi-Hat ", "47 Low-Mid Tom ", "48 Hi-Mid Tom ", "49 Crash Cymbal 1 ", "50 High Tom ", "51 Ride Cymbal 1 ", "52 Chinese Cymbal ", "53 Ride Bell ", "54 Tambourine ", "55 Splash Cymbal ", "56 Cowbell ", "57 Crash Cymbal 2 ", "58 Vibraslap ", "59 Ride Cymbal 2 ", "60 Hi Bongo", "61 Low Bongo", "62 Mute Hi Conga", "63 Open Hi Conga ", "64 Low Conga ", "65 High Timbale ", "66 Low Timbale  ", "67 High Agogo ", "68 Low Agogo ", "69 Cabasa ", "70 Maracas ", "71 Short Whistle ", "72 Long Whistle ", "73 Short Guiro ", "74 Long Guiro ", "75 Claves ", "76 Hi Wood Block ", "77 Low Wood Block ", "78 Mute Cuica ", "79 Open Cuica ", "80 Mute Triangle ", "81 Open Triangle "})
        comboBoxDrums(index).Location = New System.Drawing.Point(5, index * 28 - 8)
        comboBoxDrums(index).Size = New System.Drawing.Size(120, 24)
        comboBoxDrums(index).TabIndex = 29
        comboBoxDrums(index).Name = index

        AddHandler comboBoxDrums(index).SelectedIndexChanged, AddressOf comboBoxDrums_SelectedIndexChanged


        'New System.Windows.Forms.ComboBox()
        'Me.comboBoxDrums.FormattingEnabled = True
        'Me.comboBoxDrums.Items.AddRange(New Object() {"35 Acoustic Bass Drum", "36 Bass Drum 1", "37 Side Stick", "38 Acoustic Snare", "39 Hand Clap", "40 Electric Snare ", "41 Low Floor Tom", "42 Closed Hi-Hat", "43 High Floor Tom", "44 Pedal Hi-Hat  ", "45 Low Tom ", "46 Open Hi-Hat ", "47 Low-Mid Tom ", "48 Hi-Mid Tom ", "49 Crash Cymbal 1 ", "50 High Tom ", "51 Ride Cymbal 1 ", "52 Chinese Cymbal ", "53 Ride Bell ", "54 Tambourine ", "55 Splash Cymbal ", "56 Cowbell ", "57 Crash Cymbal 2 ", "58 Vibraslap ", "59 Ride Cymbal 2 ", "60 Hi Bongo", "61 Low Bongo", "62 Mute Hi Conga", "63 Open Hi Conga ", "64 Low Conga ", "65 High Timbale ", "66 Low Timbale  ", "67 High Agogo ", "68 Low Agogo ", "69 Cabasa ", "70 Maracas ", "71 Short Whistle ", "72 Long Whistle ", "73 Short Guiro ", "74 Long Guiro ", "75 Claves ", "76 Hi Wood Block ", "77 Low Wood Block ", "78 Mute Cuica ", "79 Open Cuica ", "80 Mute Triangle ", "81 Open Triangle "})
        'Me.comboBoxDrums.Location = New System.Drawing.Point(64, 18)
        'Me.comboBoxDrums.Name = "ComboBoxDrum1"
        'Me.comboBoxDrums.Size = New System.Drawing.Size(97, 24)
        'Me.comboBoxDrums.TabIndex = 29
    End Sub

    Private Sub comboBoxDrums_SelectedIndexChanged(sender As System.Object, e As System.EventArgs)
        Dim comboBox As System.Windows.Forms.ComboBox = DirectCast(sender, System.Windows.Forms.ComboBox)


        'txtStatus.Text = "drum cbox changed, index "

        drumNumber(CInt(comboBox.Name)) = comboBox.SelectedIndex + 35
    End Sub


    Private Sub btnAddRow_Click(sender As Object, e As EventArgs) Handles btnAddRow.Click
        
        drumAddRow()
        'picDrum.ClientRectangle.Height = 27
    End Sub

    Private Sub btnAddCol_Click(sender As Object, e As EventArgs) Handles btnAddCol.Click
        drumAddCol()
    End Sub

    Private Sub btnRemoveRow_Click(sender As Object, e As EventArgs) Handles btnRemoveRow.Click
        drumRemoveRow()
    End Sub

    Private Sub btnRemoveCol_Click(sender As Object, e As EventArgs) Handles btnRemoveCol.Click
        drumRemoveCol()
    End Sub

    Private Sub BtnRandomDrum_Click(sender As Object, e As EventArgs) Handles BtnRandomDrum.Click
        Dim i As Integer
        Randomize()
        For i = 1 To DRUM_INSTRUMENT
            comboBoxDrums(i).SelectedIndex = CInt((81 - 35) * Rnd())
            drumNumber(i) = comboBoxDrums(i).SelectedIndex
        Next
    End Sub

    Private Sub tbDrumSpeed_Scroll(sender As Object, e As EventArgs) Handles tbDrumSpeed.Scroll
        If tbDrumSpeed.Value > 0 Then
            drumSpeed = 1 - tbDrumSpeed.Value / 10.0
        Else
            drumSpeed = 1 - tbDrumSpeed.Value / 10.0
        End If
    End Sub

    Private Sub btnInverse_Click(sender As Object, e As EventArgs) Handles btnInverse.Click
        Dim i As Integer
        Dim j As Integer
        For i = 1 To DRUM_INSTRUMENT
            For j = 1 To DRUM_SLOT
                drumSlot(i, j) = Not drumSlot(i, j)
            Next
        Next
        DrawDrumConfiguration(picDrum.CreateGraphics)
    End Sub

    Private Sub btnReverse_Click(sender As Object, e As EventArgs) Handles btnReverse.Click
        Dim i As Integer
        Dim j As Integer
        Dim temp As Boolean
        For i = 1 To DRUM_INSTRUMENT
            For j = 1 To DRUM_SLOT / 2
                temp = drumSlot(i, DRUM_SLOT - j + 1)
                drumSlot(i, DRUM_SLOT - j + 1) = drumSlot(i, j)
                drumSlot(i, j) = temp
            Next
        Next
        DrawDrumConfiguration(picDrum.CreateGraphics)
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Dim i As Integer
        Dim j As Integer
        For i = 1 To DRUM_INSTRUMENT
            For j = 1 To DRUM_SLOT
                drumSlot(i, j) = False
            Next
        Next
        DrawDrumConfiguration(picDrum.CreateGraphics)

        ' btnStop_Click will stop whatever that is already playing
        btnStop_Click(sender, e)


    End Sub

    Private Sub btnLoad_Click(sender As Object, e As EventArgs) Handles btnLoad.Click
        Dim dlg As New OpenFileDialog
        Dim file As String

        ' Ask for the MIDI file
        dlg.DefaultExt = "txt"
        dlg.Filter = "Text files (*.txt)|*.txt"
        dlg.Multiselect = False

        If dlg.ShowDialog() = DialogResult.OK Then
            ' Load the file into the MIDIFile structure
            file = My.Computer.FileSystem.ReadAllText(dlg.FileName)

            txtStatus.Text = "read successful"
            'MsgBox(file)
            loadDrumFromString(file)
        End If
    End Sub

    Sub loadDrumFromString(file As String)

        Dim i As Integer
        For i = 0 To 50
            drumRemoveRow()
            drumRemoveCol()
        Next

        ' extract file into array of numbers
        'Dim lines As String()
        'lines = file.Split(System.Environment.NewLine)
        Dim numberList(10000) As Integer
        Dim numberListCount As Integer = 0

        Dim temp As String
        temp = ""
        For i = 0 To file.Length - 1
            ' if char is numeric
            If IsNumeric(file.Chars(i)) Then
                ' extract one digit number
                If i + 1 = file.Length Or (Not IsNumeric(file.Chars(i))) Then
                    numberList(numberListCount) = Val((file.Chars(i)))
                    ' extract two digit number
                Else
                    numberList(numberListCount) = Val((file.Chars(i) + file.Chars(i + 1)))
                    i += 1
                End If
                temp += numberList(numberListCount).ToString + Environment.NewLine
                numberListCount += 1
            End If

        Next

        MsgBox("load successful. Please close this box" + Environment.NewLine + "numbers extracted into array:" + Environment.NewLine + temp)



        ' Start decoding segments.
        Dim curIndex As Integer = 2
        Dim j As Integer
        ' Add rows
        For i = 2 To numberList(0)
            drumAddRow()
        Next
        ' Add cols
        For i = 2 To numberList(1)
            drumAddCol()
        Next

        For i = 1 To numberList(0)
            For j = 1 To numberList(1)
                drumSlot(i, j) = numberList(curIndex)
                curIndex += 1
            Next
            drumNumber(i) = numberList(curIndex)
            comboBoxDrums(i).SelectedIndex = drumNumber(i) - 35
            curIndex += 1
        Next

        DrawDrumConfiguration(picDrum.CreateGraphics)

        'txtStatus.Text = "first ind val = " + lines(curIndex).ToString
        'CInt(segments(0))

    End Sub

    Private Sub btnRandomBox_Click(sender As Object, e As EventArgs) Handles btnRandomBox.Click
        
        Dim i As Integer
        Dim j As Integer
        For i = 1 To DRUM_INSTRUMENT
            For j = 1 To DRUM_SLOT
                Dim randNum As Double
                randNum = Rnd()
                If (randNum <= 0.5) Then
                    drumSlot(i, j) = False
                Else
                    drumSlot(i, j) = True
                End If
            Next
        Next
        DrawDrumConfiguration(picDrum.CreateGraphics)
    End Sub

End Class
