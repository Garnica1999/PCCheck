/**
 * OSHI (https://github.com/oshi/oshi)
 *
 * Copyright (c) 2010 - 2019 The OSHI Project Team:
 * https://github.com/oshi/oshi/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.he.pc.controller.logic.hardware.oshi.hardware.platform.mac;

import com.he.pc.controller.logic.hardware.oshi.hardware.CentralProcessor;
import com.he.pc.controller.logic.hardware.oshi.hardware.ComputerSystem;
import com.he.pc.controller.logic.hardware.oshi.hardware.Display;
import com.he.pc.controller.logic.hardware.oshi.hardware.GlobalMemory;
import com.he.pc.controller.logic.hardware.oshi.hardware.HWDiskStore;
import com.he.pc.controller.logic.hardware.oshi.hardware.NetworkIF;
import com.he.pc.controller.logic.hardware.oshi.hardware.PowerSource;
import com.he.pc.controller.logic.hardware.oshi.hardware.Sensors;
import com.he.pc.controller.logic.hardware.oshi.hardware.SoundCard;
import com.he.pc.controller.logic.hardware.oshi.hardware.UsbDevice;
import com.he.pc.controller.logic.hardware.oshi.hardware.common.AbstractHardwareAbstractionLayer;

public class MacHardwareAbstractionLayer extends AbstractHardwareAbstractionLayer {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public ComputerSystem getComputerSystem() {
        if (this.computerSystem == null) {
            this.computerSystem = new MacComputerSystem();
        }
        return this.computerSystem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CentralProcessor getProcessor() {
        if (this.processor == null) {
            this.processor = new MacCentralProcessor();
        }
        return this.processor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GlobalMemory getMemory() {
        if (this.memory == null) {
            this.memory = new MacGlobalMemory();
        }
        return this.memory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerSource[] getPowerSources() {
        return MacPowerSource.getPowerSources();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HWDiskStore[] getDiskStores() {
        return new MacDisks().getDisks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Display[] getDisplays() {
        return MacDisplay.getDisplays();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sensors getSensors() {
        if (this.sensors == null) {
            this.sensors = new MacSensors();
        }
        return this.sensors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetworkIF[] getNetworkIFs() {
        return new MacNetworks().getNetworks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UsbDevice[] getUsbDevices(boolean tree) {
        return MacUsbDevice.getUsbDevices(tree);
    }

    /**
     * Instantiates an array of {@link SoundCard} objects, representing the
     * Sound cards.
     *
     * @return An array of SoundCard objects or an empty array if none are
     *         present.
     */
    @Override
    public SoundCard[] getSoundCards() {
        return MacSoundCard.getSoundCards().toArray(new SoundCard[0]);
    }
}
