#!/bin/sh

rpi_os_dir=raspberry-pi-os

do_mount_overlayfs()
{
    local serial_num=$1
    mkdir -p ${serial_num}

    lower=${rpi_os_dir}/boot
    upper=upper/${serial_num}
    work=work/${serial_num}
    mkdir -p $upper
    mkdir -p $work

    # does not need nfs for boot partion (tftp only)
    mount -t overlay overlay -o lowerdir=${lower},upperdir=${upper},workdir=${work} ${serial_num}
}

if [ ! -d ${rpi_os_dir} ]; then
    echo "lowerdir ${rpi_os_dir} does not exist. exit."
    exit 0
fi

if [ ! -d ${rpi_os_dir}/boot ]; then
    echo "lowerdir ${rpi_os_dir}/boot does not exist. exit."
    exit 0
fi

do_mount_overlayfs a0000000
do_mount_overlayfs b0000000
do_mount_overlayfs c0000000
