'
' class storing information of each channel.
'
'
'
'
'
'


Public Class midiChannel
    'attributes
    Public channel As Integer
    Public instrument As Integer
    Public bankMSB As Integer
    Public panning As Integer


    Public Sub New(ByVal ch As Integer)
        instrument = 0
        panning = 64
        channel = ch
        bankMSB = 0
        'volume =
    End Sub

    Public Sub changeInstrument(ByVal instrumentNumber As Integer)
        Dim midimsg As Integer

        instrument = instrumentNumber
        midimsg = (instrument * &H100) + &HC0 + channel

        frmMidiPiano.generateMidiSignal(midimsg)
    End Sub

    Public Sub changePanning(ByVal pan As Integer)
        Dim midimsg As Integer

        panning = pan
        midimsg = (panning * &H10000) + (&HA * &H100) + &HB0 + channel

        frmMidiPiano.generateMidiSignal(midimsg)

    End Sub

    Public Sub changeBankMSB(ByVal bank As Integer)
        Dim midimsg As Integer

        bankMSB = bank
        midimsg = &HB0 + channel + (&H0 * &H100) + (bankMSB * &H10000)
        frmMidiPiano.generateMidiSignal(midimsg)

        ' Resend a program change message for the instrument so that it comes into effect immediately
        midimsg = &HC0 + (instrument * &H100) + channel
        frmMidiPiano.generateMidiSignal(midimsg)

    End Sub

    Public Sub resetChannel()
        changeInstrument(0)
        changePanning(64)
        changeBankMSB(0)
    End Sub

End Class
